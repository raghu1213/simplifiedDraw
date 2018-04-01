package com.practice.draw.command;

import com.practice.draw.utils.Result;

public class NotSupportedCommand  extends CommandBase{
    public NotSupportedCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute() {

    }

    @Override
    public Result getResults() {
        return new Result(false,null,this.commandString + ": Not supported");
    }
}
