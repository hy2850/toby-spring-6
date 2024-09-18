package com.hcpark.tobyspring6.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor {

    @Override
    public String executeApi(URI uri) throws IOException {
        var connection = (HttpURLConnection) uri.toURL().openConnection();
        var br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return br.lines().collect(Collectors.joining());
    }
}
