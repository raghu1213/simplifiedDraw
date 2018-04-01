package com.practice.draw.utils;

public class Point implements Comparable<Point>{
    public Point(int x, int y, String color,boolean isShape) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.isShape= isShape;
    }

    public Point(int x, int y,String color){
        this(x,y,color,true);
    }

    public Point(int x, int y){
        this(x,y,"",true);
    }

    int x;
    int y;
    String color;
    boolean isShape;

    public boolean isShape() {
        return isShape;
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("Point-->{%s},{%s},{%s}",this.getX(),this.getY(),this.getColor());
    }

    @Override
    public boolean equals(Object obj) {
        Point other = (Point)obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int compareTo(Point other) {
        int result;
        if (this.y < other.y){
            result = -1;
        }
        else if (this.y> other.y){
            result = 1;
        }
        else{
            if (this.x< other.x)
                result = -1;
            else if(this.x > other.x){
                result = 1;
            }
            else{
                result = 0;
            }
        }

        return  result;
    }
}
