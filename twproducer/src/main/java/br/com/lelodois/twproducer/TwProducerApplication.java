package br.com.lelodois.twproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TwProducerApplication {

    private Logger logger = LoggerFactory.getLogger(TwProducerApplication.class);

    private final TwClientWrapper client;
    private final TwProducer producer;

    public TwProducerApplication(List<String> terms, String clientName, String topic) {
        client = new TwClientWrapper(clientName, terms);
        producer = new TwProducer(topic);
    }

    public void start() {
        logger.info("Start");

        logger.info("Connected");

        while (!client.isDone()) {
            String message = client.poll(5, TimeUnit.SECONDS);
            if (message != null) {
                logger.info("Sending message: " + message);
                producer.send(
                        message,
                        (recordMetadata, exc) -> {
                            if (exc != null) {
                                logger.error("Deu ruim", exc);
                            }
                        }
                );
            }
        }
        client.stop();
        logger.info("End");
    }
}
