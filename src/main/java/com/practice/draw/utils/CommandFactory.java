package com.practice.draw.utils;

import com.practice.draw.SupportedCommands;
import com.practice.draw.command.*;

public class CommandFactory {

    private static CoordinateGenerator coordinateGenerator= new DefaultCoordinateGenerator();


    public static Command createCommand(String inputCommand) {
        String commandKey  = inputCommand.split(" ")[0];
        Command command;
        switch (commandKey.toUpperCase()){
            case SupportedCommands.CANVAS:
                command = new CanvasCommand(inputCommand,coordinateGenerator);
                break;
            case SupportedCommands.RECTANGLE:
                command = new RectangleCommand(inputCommand,coordinateGenerator);
                break;
            case SupportedCommands.LINE:
                command = new LineCommand(inputCommand,coordinateGenerator);
                break;
            case SupportedCommands.FILL:
                command = new FillCommand(inputCommand);
                break;
            case SupportedCommands.QUIT:
                command = new QuitCommand();
                break;
            default:
                command = new NotSupportedCommand(inputCommand);
                break;
        }

        return command;
    }
}
