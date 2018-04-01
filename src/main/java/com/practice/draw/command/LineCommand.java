package com.practice.draw.command;

import com.practice.draw.utils.Result;
import com.practice.draw.utils.CoordinateGenerator;
import com.practice.draw.utils.Point;

import java.util.List;

public class LineCommand extends CommandBase {

    private final CoordinateGenerator coordinateGenerator;

    public LineCommand(String args, CoordinateGenerator coordinateGenerator){
        super(args);

        this.coordinateGenerator = coordinateGenerator;
    }

    int x1;
    int y1;
    int x2;
    int y2;

    @Override
    public void execute() {
        if (this.validate(this.getCommandString(),5)) {
            init();
            List<Point> allPoints = this.coordinateGenerator.getCooridnatesBetweenTwoPoints(x1, y1, x2, y2, "x");
            result = new Result(true, allPoints, "Success");
        } else {
            result = new Result(false, null, "Invalid Line Command." +
                    "\n USAGE: L <x1> <y1> <x2> <y2>" +
                    "\n     Takes 4 arguments"+
                    "\n     All arguments must be unsigned integers");
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
