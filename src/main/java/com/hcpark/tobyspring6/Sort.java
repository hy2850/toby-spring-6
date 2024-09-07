package com.hcpark.tobyspring6;

import java.util.List;

public class Sort {

    public List<String> sortByLength(List<String> list) {
        // Comparator 인스턴스를 람다 표현식으로 구현
        list.sort((str1, str2) -> str1.length() - str2.length());
        return list;
    }
}
