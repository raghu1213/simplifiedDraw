package com.practice.draw.command;

import com.practice.draw.utils.Result;

public abstract class CommandBase implements Command{


    public String commandString;
    public Result result;

    public CommandBase(String inputCommand) {

        this.commandString = inputCommand;
    }



    protected boolean validate(String inputCommand,int length) {
        String[] args = inputCommand.split(" ");
        boolean isValid = true;
        if (args.length!=length) {
            isValid = false;
        }
        else {
            // just checking if rest of the params are valid integers
            for (int i = 1; i < args.length; i++) {
                try {
                    Integer.parseUnsignedInt(args[i]);
                    isValid = true;
                } catch (Exception ex) {
                    isValid = false;
                    break;
                }
            }
        }

        return isValid;


    }


    @Override
    public String getCommandString() {
        return commandString;
    }
}
