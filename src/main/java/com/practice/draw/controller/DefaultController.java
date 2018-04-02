package com.practice.draw.controller;

import com.practice.draw.command.Command;
import com.practice.draw.command.Container;
import com.practice.draw.command.Filler;
import com.practice.draw.utils.Point;
import com.practice.draw.utils.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultController implements CommandController {
    private List<Command> commandList ;
    private Container container;
    public DefaultController(){
        commandList = new ArrayList<>();
    }

    @Override
    public Result execute(Command command) {
        if (command instanceof Container) {
            if (commandList.size() > 0){
                commandList.clear();
            }
            this.container = (Container) command;
        }

        if (command instanceof Filler) {
            List<Point> allPoints = this.getResults()
                    .stream()
                    .filter(r -> r.isSuccessful())
                    .flatMap(r -> r.getPoints().stream())
                    .collect(Collectors.toList());
            ((Filler) command).setState(allPoints);
        }

        command.setValidator(container.getBoundaryValidator());

        command.execute();

        Result result = command.getResults();
        if (result.isSuccessful()) {
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
