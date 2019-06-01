package br.com.lelodois.twproducer;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Producer {

    private List<String> terms;
    private String name;

    private Logger logger = LoggerFactory.getLogger(Producer.class);

    public Producer(List<String> terms, String name) {
        this.terms = terms;
        this.name = name;
    }

    public static void main(String[] args) {
        new Producer(
                Lists.newArrayList("bitcoin"),
                "twclient-01"
        ).run();
    }

    private void run() {
        logger.info("Start");

        TwClientWrapper client = new TwClientWrapper(name, terms);
        logger.info("Connected");

        while (!client.isDone()) {
            String message = client.poll(5, TimeUnit.SECONDS);
            logger.info(message);
        }
        client.stop();
        logger.info("End");
    }
}
