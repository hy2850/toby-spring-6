package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExRateProvider {

    BigDecimal getExchangeRate(String currency) throws IOException;
}
