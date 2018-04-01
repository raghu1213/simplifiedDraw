package com.practice.draw.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DefaultCoordinateGenerator implements CoordinateGenerator {
    @Override
    public List<Point> getCooridnatesBetweenTwoPoints(int x1, int y1, int x2, int y2, String color){
        List<Point> coordinates = new ArrayList<>();
        int yDiff = y2 - y1;
        int xDiff = x2 - x1;


        boolean isXSame = xDiff == 0;
        int slope = isXSame? 0 : yDiff / xDiff;

        //length of the line
        double distance = Math.floor(Math.round(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))));
        for (int i = 0; i <= distance; i++)
        {
            double y =  slope == 0 && !isXSame ? 0 : yDiff * (i / distance);
            double x =  slope == 0 ? xDiff * (i / distance) : y / slope;
            Point point = new Point ((int)Math.round(x) + x1, (int)Math.round(y)+y1, color);
            coordinates.add(point);
        }

        return  coordinates;
    }

    @Override
    public List<Point> getRectangle(Point topLeft, Point bottomRight, String hColor, String vColor)
    {
        Point topRight = new Point(bottomRight.x,topLeft.y);
        Point bottomLeft = new Point(topLeft.x,bottomRight.y);
        Set<Point> coordinates= new TreeSet<>();
        List<Point> topLine = getCooridnatesBetweenTwoPoints(topLeft.x,topLeft.y,topRight.x, topRight.y, hColor);
        List<Point> bottomLine = getCooridnatesBetweenTwoPoints(bottomLeft.x,bottomLeft.y,bottomRight.x, bottomRight.y, hColor);
        List<Point> leftLine = getCooridnatesBetweenTwoPoints(topLeft.x,topLeft.y,bottomLeft.x, bottomLeft.y, vColor);
        List<Point> rightLine = getCooridnatesBetweenTwoPoints(topRight.x, topRight.y,bottomRight.x, bottomRight.y, vColor);

        coordinates.addAll(topLine);
        coordinates.addAll(bottomLine);
        coordinates.addAll(leftLine);
        coordinates.addAll(rightLine);

        return new ArrayList<>(coordinates);
    }
}