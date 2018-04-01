package com.practice.draw.controller;

import com.practice.draw.command.Command;
import com.practice.draw.command.Container;
import com.practice.draw.command.Filler;
import com.practice.draw.utils.Point;
import com.practice.draw.utils.Result;
import com.practice.draw.validator.Boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultController implements CommandController {
   // private List<Result> results;
    private List<Command> commandList ;

    public DefaultController(){
        commandList = new ArrayList<>();
    }

    @Override
    public Result execute(Command command) {
        if (command instanceof Container) {
            if (commandList.size() > 0){
                commandList.clear();
            }
        }
        if (command instanceof Filler) {
            List<Point> allPoints = this.getResults().stream().filter(r -> r.isSuccess()).flatMap(r -> r.getPoints().stream()).collect(Collectors.toList());

            Boundary boundary = ((Container) this.commandList.stream().filter(c -> c instanceof Container).findFirst().get()).getBoundary();

            ((Filler) command).setState(allPoints, boundary);
        }

        command.execute();

        Result result = command.getResults();
        if (result.isSuccess()) {
            commandList.add(command);
        }
        return result;
    }

    @Override
    public List<Result> getResults() {
        return commandList.stream().map(c->c.getResults())
                .collect(Collectors.toList());
    }


}
