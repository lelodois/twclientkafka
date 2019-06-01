package br.com.lelodois.twproducer;

import com.google.common.collect.Lists;

public class Main {
    public static void main(String[] args) {
        new TwProducerApplication(
                Lists.newArrayList("bitcoin"),
                "twclient-01",
                "tw_tweets"
        ).start();
    }
}
