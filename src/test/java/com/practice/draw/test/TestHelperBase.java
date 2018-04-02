package com.practice.draw.test;

import com.practice.draw.command.Command;
import com.practice.draw.utils.Result;
import org.junit.Assert;


public class TestHelperBase {
    protected void printCommandString(Command command){
        System.out.println("Command:" + command.getCommandString());
    }

    protected void printResults(Result result)
    {
        System.out.println("Message:" + result.getMessage());
        System.out.println(result.toString());
    }

    protected void invalidCommandTest(Command command) {
        command.execute();
        Result result = command.getResults();
        Assert.assertNull(result.getPoints());
        Assert.assertFalse(result.isSuccessful());
        Assert.assertNotEquals("",result.getMessage());
        printResults(result);
    }
}
