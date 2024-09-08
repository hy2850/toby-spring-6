package com.hcpark.tobyspring6.learningtest;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClockTest {

    @Test
    void testClock() {
        // given
        var clock = Clock.systemDefaultZone();

        // when
        var dt1 = LocalDateTime.now(clock);
        var dt2 = LocalDateTime.now(clock);

        // then
        Assertions.assertThat(dt1).isBefore(dt2);
    }

    @Test
    void testFixedClock() {
        // given
        var clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        // when
        var dt1 = LocalDateTime.now(clock);
        var dt2 = LocalDateTime.now(clock);
        var dt3 = LocalDateTime.now(clock).plusHours(1);

        // then
        Assertions.assertThat(dt1).isEqualTo(dt2);
        Assertions.assertThat(dt3).isEqualTo(dt1.plusHours(1));
    }

}
