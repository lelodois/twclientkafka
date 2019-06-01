package br.com.lelodois.twproducer;

import com.twitter.hbc.httpclient.auth.OAuth1;

public class TwAuthenticate {
    private final OAuth1 oauth;

    public TwAuthenticate() {
        String apiKey = "3oPPwa4B37RqvXl3DRcYhdbmt";
        String apiKeySecret = "g87lScJy7Dy8BjKeWjgVluaLNU5ZORUZ8dKJYN02Czv1mk5Cbn";
        String accessToken = "68868975-VOA9SIVnslpV6Cu1USKCLQ56HjLgUBmnkRqWapyHF";
        String accessTokenSecret = "jKqGn2w0ZRcs7qSb2vS6IC6FczjJfJIvV91uWKcUJQmDr";
        oauth = new OAuth1(apiKey, apiKeySecret, accessToken, accessTokenSecret);
    }

    public OAuth1 getOauth() {
        return oauth;
    }
}
