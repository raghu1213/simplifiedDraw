package com.practice.draw.utils;

import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Result {
    public Result(boolean isSuccessful, List<Point> points, String message) {
        this.isSuccessful = isSuccessful;
        this.points = points;
        this.message = message;
    }
    public Result(){

    }

    boolean isSuccessful;
    List<Point> points;
    String message ;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {

        if (this.getPoints() == null) return "";

        Point min = points.stream().min(Comparator.naturalOrder()).get();
        Point max = points.stream().max(Comparator.naturalOrder()).get();

        int totalWidth = max.getX() - min.getX();
        int totalHeight = max.getY() - min.getY();

        StringJoiner stringJoiner= new StringJoiner("");

        for (int i = min.getY(); i <= min.getY()+ totalHeight; i++) {
            for (int j = min.getX(); j <= min.getX()+totalWidth; j++) {
                int finalJ = j;
                int finalI = i;

                Supplier<Stream<Point>> pointsSupplier = () -> this.points.stream().filter(p-> p.getX() == finalJ && p.getY() == finalI);

                long count = pointsSupplier.get().count();
                if (count ==1 ){
                    stringJoiner.add(pointsSupplier.get().findFirst().get().getColor());
                }
                else if (count>1)
                {
                    stringJoiner.add(pointsSupplier.get().skip(count-1).findFirst().get().getColor());
                }
                else{
                    stringJoiner.add(" ");
                }
            }
            stringJoiner.add("\n");
        }

        return stringJoiner.toString();
    }
}
