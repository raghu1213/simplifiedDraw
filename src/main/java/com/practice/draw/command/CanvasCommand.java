package com.practice.draw.command;

import com.practice.draw.validator.Boundary;
import com.practice.draw.validator.BoundaryValidator;
import com.practice.draw.utils.Result;
import com.practice.draw.utils.CoordinateGenerator;
import com.practice.draw.utils.Point;

import java.util.List;

public class CanvasCommand extends CommandBase implements Container {

    private int width;
    private int height;
    private CoordinateGenerator coordinateGenerator;

    public CanvasCommand(String inputCommand, CoordinateGenerator defaultCoordinateGenerator) {
        super(inputCommand);
        this.coordinateGenerator = defaultCoordinateGenerator;

    }

    @Override
    public void execute() {
        if (this.validate(this.getCommandString(),3)){
            init();
            Point topLeft = new Point(0,0);
            Point bottomRight = new Point(width+1,height+1);

            List<Point> allPoints = coordinateGenerator.getRectangle(topLeft,bottomRight,"-","|");
                    
            this.result= new Result(true,allPoints,"Success");

        }
        else {
            this.result = new Result(false, null, "Invalid Canvas Command." +
                    "\nUSAGE: C <w> <h>" +
                    "\n     Takes 2 arguments"+
                    "\n     w: Unsigned integer value for width" +
                    "\n     h: Unsigned integer value for height");
        }
    }

    @Override
    public Result getResults() {
        return this.result;
    }

    private void init() {
        String args[] = this.getCommandString().split(" ");
        width = Integer.parseInt(args[1]);
        height = Integer.parseInt(args[2]);
    }


    @Override
    public Boundary getBoundary() {
        Point topLeft = new Point(0,0);
        Point bottomRight = new Point(width+1,height+1);
        return new BoundaryValidator(topLeft,bottomRight);
    }
}
