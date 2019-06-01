package br.com.lelodois.twproducer;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.twitter.hbc.core.Constants.STREAM_HOST;

public class TwClientWrapper {

    private final BasicClient client;
    private final BlockingQueue<String> blocking;

    public TwClientWrapper(String name, List<String> terms) {
        StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
        endpoint.trackTerms(terms);
        this.blocking = new LinkedBlockingQueue<>(1000);
        this.client = new ClientBuilder()
                .name(name)
                .authentication(new TwAuthenticate().getOauth())
                .hosts(new HttpHosts(STREAM_HOST))
                .endpoint(endpoint)
                .processor(new StringDelimitedProcessor(blocking))
                .build();
        this.client.connect();
    }


    public boolean isDone() {
        return client.isDone();
    }

    public void stop() {
        client.stop();
    }

    public String poll(int time, TimeUnit timeUnit) {
        try {
            return blocking.poll(time, timeUnit);
        } catch (InterruptedException e) {
            this.stop();
            throw new RuntimeException("Stop the client");
        }
    }
}
