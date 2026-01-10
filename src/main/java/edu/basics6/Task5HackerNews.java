package edu.basics6;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public final class Task5HackerNews {
    private Task5HackerNews() {
    }

    @SuppressWarnings("MagicNumber")
    private static String getSimpleResponse(String reqUri) {
        URI uri;
        try {
            uri = new URI(reqUri);
        } catch (URISyntaxException e) {
            return null;
        }

        var request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .header("AcceptEncoding", "gzip")
            .timeout(Duration.of(10, ChronoUnit.SECONDS))
            .build();

        HttpResponse<String> response;
        try {
            response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            return null;
        }
        return response.body();
    }

    public static long[] hackerNewsTopStories() {
        var respBody = getSimpleResponse("https://hacker-news.firebaseio.com/v0/topstories.json");

        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(respBody);
        ArrayList<String> topicsId = new ArrayList<>();

        while (matcher.find()) {
            if (matcher.start() == matcher.end()) {
                continue;
            }
            topicsId.add(respBody.substring(matcher.start(), matcher.end()));
        }

        long[] topicNumbers = new long[topicsId.size()];
        for (int i = 0; i < topicNumbers.length; ++i) {
            topicNumbers[i] = Long.parseLong(topicsId.get(i));
        }
        return topicNumbers;
    }

    public static String news(long id) {
        var respBody = getSimpleResponse("https://hacker-news.firebaseio.com/v0/item/" + id + ".json");

        Pattern pattern = Pattern.compile("\"title\":(.*),");
        Matcher matcher = pattern.matcher(respBody);

        matcher.find();
        var stringWithCol = matcher.group(1).split(",")[0];
        return stringWithCol.substring(1, stringWithCol.length() - 2);
    }
}
