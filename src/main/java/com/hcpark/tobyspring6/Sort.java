package com.hcpark.tobyspring6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {

    public static void main(String[] args) {
        List<String> arr = Arrays.asList("a", "b", "spring", "java");

//        Collections.sort(arr, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.length() - o2.length();
//            }
//        });

        // Comparator 인스턴스를 람다 표현식으로 구현
        Collections.sort(arr, (str1, str2) -> str1.length() - str2.length());

        arr.forEach(System.out::println);
    }
}
