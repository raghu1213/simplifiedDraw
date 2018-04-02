package com.practice.draw.command;


import com.practice.draw.utils.Result;
import com.practice.draw.validator.Validator;

public interface Command
{
    void  execute();
    void setValidator(Validator validator);
    Result getResults();
    String getCommandString();


}
