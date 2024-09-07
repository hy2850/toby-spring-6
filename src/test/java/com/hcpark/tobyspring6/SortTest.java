package com.hcpark.tobyspring6;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortTest {

    Sort sort; // @Test 마다 SortTest 인스턴스 새로 생성되므로, @Test 끼리 데이터 안겹침

    @BeforeEach
    void beforeEach() {
        sort = new Sort();
        System.out.println(this);
    }

    @Test
    void sortTest1() {
        // given
        var list = Arrays.asList("aa", "b");

        // when
        var result = sort.sortByLength(list);

        // then
        Assertions.assertThat(result).isEqualTo(Arrays.asList("b", "aa"));
    }

    @Test
    void sortTest2() {
        // given
        var list = Arrays.asList("aa", "b", "ccc");

        // when
        var result = sort.sortByLength(list);

        // then
        Assertions.assertThat(result).isEqualTo(Arrays.asList("b", "aa", "ccc"));
    }

    @Test
    void sortTest3() {
        // given
        var list = Arrays.asList("aaa", "b", "cc");

        // when
        var result = sort.sortByLength(list);

        // then
        Assertions.assertThat(result).isEqualTo(Arrays.asList("b", "cc", "aaa"));
    }
}
