package com.practice.draw.command;


import com.practice.draw.utils.Result;

public interface Command
{
    void  execute();
    Result getResults();
    String getCommandString();

}
