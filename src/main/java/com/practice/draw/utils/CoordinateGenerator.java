package com.practice.draw.utils;

import java.util.List;

public interface CoordinateGenerator {
  List<Point> getCooridnatesBetweenTwoPoints(int x1, int y1, int x2, int y2, String color);

  List<Point> getRectangle(Point topLeft, Point bottomRight, String hColor, String vColor);
}
