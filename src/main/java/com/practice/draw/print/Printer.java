package com.practice.draw.print;

import com.practice.draw.utils.Result;

import java.util.List;
import java.util.stream.Collectors;

public class Printer {
    public static void print(List<Result> results){
        Result result = new Result(true, results.stream()
                .flatMap(r->r.getPoints().stream())
                .collect(Collectors.toList()),"Success");
        System.out.println(result);
    }
}
