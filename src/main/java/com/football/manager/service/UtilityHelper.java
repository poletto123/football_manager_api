package com.football.manager.service;

import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UtilityHelper {

    public static Sort createSortObject(String[] sort) {
        return Sort.by(Arrays.stream(sort)
                .map(s -> s.startsWith("-") ? Sort.Order.desc(s.substring(1)) : Sort.Order.asc(s))
                .collect(Collectors.toList()));
    }

}
