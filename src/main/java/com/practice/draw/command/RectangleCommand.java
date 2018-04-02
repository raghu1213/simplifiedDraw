package com.practice.draw.command;

import com.practice.draw.utils.Result;
import com.practice.draw.utils.CoordinateGenerator;
import com.practice.draw.utils.Point;
import com.practice.draw.validator.Validator;

import java.util.List;

public class RectangleCommand extends CommandBase {
    private final CoordinateGenerator coordinateGenerator;

    public RectangleCommand(String inputCommand, CoordinateGenerator coordinateGenerator) {
        super(inputCommand);
        this.coordinateGenerator = coordinateGenerator;
    }

    int x1;
    int y1;
    int x2;
    int y2;

    @Override
    public void execute() {
        if (this.validateInputCommand(this.getCommandString(), 5)) {
            init();
            Point topLeft = new Point(x1, y1);
            Point bottomRight = new Point(x2, y2);
            if (getBoundaryValidator() != null
                    && !getBoundaryValidator().validate(topLeft)
                    && !getBoundaryValidator().validate(bottomRight)) {
                this.result = new Result(false, null, "Rectangle is not within container");
            } else {
                List<Point> allPoints = coordinateGenerator.getRectangle(topLeft, bottomRight, "x", "x");
                this.result = new Result(true, allPoints, "Success");
            }
        } else {
            this.result = new Result(false, null, "Invalid Rectangle Command." + "\n USAGE: R <x1> <y1> <x2> <y2>" + "\n       Takes 4 arguments" + "\n       All arguments must be unsigned integers");
        }
    }


    @Override
    public Result getResults() {
        return this.result;
    }

    private void init() {
        String args[] = this.getCommandString().split(" ");
        this.x1 = Integer.parseInt(args[1]);
        this.y1 = Integer.parseInt(args[2]);
        this.x2 = Integer.parseInt(args[3]);
        this.y2 = Integer.parseInt(args[4]);

    }
}
