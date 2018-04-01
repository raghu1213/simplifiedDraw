package com.practice.draw.controller;

import com.practice.draw.command.Command;
import com.practice.draw.utils.Result;

import java.util.List;

public interface CommandController {
    Result execute(Command command);
    List<Result> getResults();

}
