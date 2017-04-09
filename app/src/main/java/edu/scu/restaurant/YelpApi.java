package edu.scu.restaurant;

/**
 * Created by yuhaowang on 4/3/17.
 */

import android.util.Log;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class YelpApi {
    private static final String API_HOST = "api.yelp.com";
    private static final String SEARCH_PATH = "/v2/search";
    private static final String CONSUMER_KEY = "DADReTj5rGMMiCCMbOMxcA";
    private static final String CONSUMER_SECRET = "zPm7EK7WglFjzgjpRMoLtbDrogk";
    private static final String TOKEN = "DSQPWT1eRmYW4NTsuruCbu6ycU5lPMKv";
    private static final String TOKEN_SECRET = "i0GF26n4ttxJ8A0uNJVPYaB3i9c";

    private OAuthService service;
    private Token accessToken;

    /**
     * Setup the Yelp API OAuth credentials.
     */
    public YelpApi() {
        this.service = new
                ServiceBuilder().provider(TwoStepOAuth.class).apiKey(CONSUMER_KEY)
                .apiSecret(CONSUMER_SECRET).build();
        this.accessToken = new Token(TOKEN, TOKEN_SECRET);
    }

    /**
     * Fire a search request to Yelp API.
     */
    public String searchForBusinessesByLocation(String term, String location, int searchLimit) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(searchLimit));
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        Log.i("message", response.getBody());
        return response.getBody();
    }
}
