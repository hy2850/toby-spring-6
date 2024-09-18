package com.hcpark.tobyspring6.api;

import java.io.IOException;
import java.net.URI;

public interface ApiExecutor {

    String executeApi(URI uri) throws IOException;
}
