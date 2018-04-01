package com.practice.draw.command;

import com.practice.draw.utils.Point;
import com.practice.draw.validator.Boundary;

import java.util.List;

public interface Filler {
  void setState(List<Point> childCoordinates, Boundary boundary);
}
