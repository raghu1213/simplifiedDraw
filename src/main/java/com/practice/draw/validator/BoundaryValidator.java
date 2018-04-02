package com.practice.draw.validator;

import com.practice.draw.utils.Point;

public class BoundaryValidator implements Validator {

  private final Point start;
  private final Point end;

  public BoundaryValidator(Point start, Point end){

    this.start = start;
    this.end = end;
  }

  @Override
  public boolean validate(Point point) {
    return point.getX()> this.start.getX() && point.getX() < this.end.getX()
            && point.getY() > this.start.getY() && point.getY() < this.end.getY();

  }
}
