package com.hcpark.tobyspring6;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SortTest {

    @Test
    void sortTest1() {
        // given
        var sort = new Sort();
        var list = Arrays.asList("aa", "b");

        // when
        var result = sort.sortByLength(list);

        // then
        Assertions.assertThat(result).isEqualTo(Arrays.asList("b", "aa"));
    }

    @Test
    void sortTest2() {
        // given
        var sort = new Sort();
        var list = Arrays.asList("aa", "b", "ccc");

        // when
        var result = sort.sortByLength(list);

        // then
        Assertions.assertThat(result).isEqualTo(Arrays.asList("b", "aa", "ccc"));
    }

    @Test
    void sortTest3() {
        // given
        var sort = new Sort();
        var list = Arrays.asList("aaa", "b", "cc");

        // when
        var result = sort.sortByLength(list);

        // then
        Assertions.assertThat(result).isEqualTo(Arrays.asList("b", "cc", "aaa"));
    }
}
