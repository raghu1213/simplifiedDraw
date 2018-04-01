package com.practice.draw.command;

import com.practice.draw.validator.Boundary;
import com.practice.draw.utils.Result;
import com.practice.draw.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class FillCommand extends CommandBase  implements Filler {


  private  List<Point> childCoordinates;
  private Boundary boundary;
  private List<Point> filledCoordinates;
  private List<Point> fillCoordinates;
  private int x;
  private int y;
  private String color;


  public FillCommand(String inputCommand){
    super(inputCommand);
    this.filledCoordinates = new ArrayList<>();
    fillCoordinates = new ArrayList<>();
    this.childCoordinates = new ArrayList<>();
  }

  @Override
  public void execute() {
    if (this.validate(this.getCommandString(),4)) {
      init();
      this.fill(this.x, this.y, this.color);
      result = new Result(true, this.fillCoordinates, "Success");
    }
    else{
        result = new Result(false, null, "Invalid Bucket Fill Command." +
                "\n USAGE: B <x> <y> <c>" +
                "\n     Takes 3 arguments"+
                "\n     <x> <y> : Valid unsigned integers" +
                "\n     <c> : color to fill the shape with");
    }
  }

  @Override
  public Result getResults() {
    return this.result;
  }

  @Override
  public void setState(List<Point> childCoordinates, Boundary boundary) {
    this.childCoordinates = childCoordinates;
    this.boundary = boundary;
  }

  private void fill(int x, int y, String color) {

    if (!boundary.validate(new Point(x,y,color))) return;

    Point p =new Point(x,y,color,false);
    if (this.childCoordinates.stream().anyMatch(point->point.isShape() && point.equals(p))){
      return;
    }

    if (filledCoordinates.contains(p)){
      Point existing = this.filledCoordinates.get(this.filledCoordinates.indexOf(p));
      if (existing.getColor().equals(color)) return;
      filledCoordinates.remove(p);//clear the old filled point. This is required when fill is called again with new color
    }

    this.fillCoordinates.add(p);
    this.filledCoordinates.add(p);
    this.fill(x-1,y,color);
    this.fill(x+1,y,color);
    this.fill(x,y-1,color);
    this.fill(x,y+1,color);
  }

  private void init() {
    String args[] = this.getCommandString().split(" ");
    this.x = Integer.parseInt(args[1]);
    this.y = Integer.parseInt(args[2]);
    this.color = args[3];

  }

  @Override
  protected boolean validate(String inputCommand, int length) {
    String[] args = inputCommand.split(" ");
    boolean isValid = true;
    if (args.length!=length) {
      isValid = false;
    }
    else {
      // just checking if rest of the params are valid integers
      for (int i = 1; i < args.length-1; i++) {
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
}
