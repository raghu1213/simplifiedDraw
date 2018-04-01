package com.practice.draw.test.command;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import com.practice.draw.test.TestHelperBase;
import com.practice.draw.command.FillCommand;
import com.practice.draw.validator.Boundary;
import com.practice.draw.validator.BoundaryValidator;
import com.practice.draw.utils.Result;
import com.practice.draw.utils.Point;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static org.hamcrest.core.Is.is;

@RunWith(Spectrum.class)
public class FillCommandTest extends TestHelperBase {
  {
    feature("Bucket Fill", () -> {
      final Variable<FillCommand> command = new Variable<>();

      scenario("Only one shape provided", () -> {
        final Variable<List<Point>> emptyCanvas = new Variable<>();
        final Variable<Boundary> boundryValidator = new Variable<>();
        given("An empty canvas", () -> {
          emptyCanvas.set(getEmptyCanvas());
        });
        and("A validator for container", () -> {
          boundryValidator.set(new BoundaryValidator(emptyCanvas.get().stream().min(Comparator.naturalOrder()).get(), emptyCanvas.get().stream().max(Comparator.naturalOrder()).get()));
        });
        when("Color is o with valid start point", () -> {
            command.set(new FillCommand("B 1 1 o"));
            command.get().setState(emptyCanvas.get(), boundryValidator.get());
            printCommandString(command.get());

        });
        then("Fill the shape with color o", () -> {
          String expectedShape =  "oooo\n" +
                                  "oooo\n" +
                                  "oooo\n" ;
           command.get().execute();
           Result result = command.get().getResults();
          Assert.assertThat(result.toString(), is(expectedShape));

          printResults(result);

        });
      });
      scenario("Invalid parameteres are supplied",()->{
          when("2nd and 3rd args are not integers",()->{
              command.set(new FillCommand("B b 1 o"));
              printCommandString(command.get());
          });
          then("Error message is printed",()->invalidCommandTest(command.get()));

          when("2nd and 3rd args are -ve",()->{
              command.set(new FillCommand("B -1 -1 o"));
              printCommandString(command.get());
          });
          then("Error message is printed",()->invalidCommandTest(command.get()));

          when("Args length is not correct",()->{
              command.set(new FillCommand("B 1 o"));
              printCommandString(command.get());
          });
          then("Error message is printed",()->invalidCommandTest(command.get()));

      });

    });

  }

  private List<Point> getEmptyCanvas() {
      List<Point> points = new ArrayList<Point>() ;
      points.add(new Point(0,0,"-"));
      points.add(new Point(1,0,"-"));
      points.add(new Point(2,0,"-"));
      points.add(new Point(3,0,"-"));
      points.add(new Point(4,0,"-"));
      points.add(new Point(5,0,"-"));
      points.add(new Point(0,1,"|"));
      points.add(new Point(5,1,"|"));
      points.add(new Point(0,2,"|"));
      points.add(new Point(5,2,"|"));
      points.add(new Point(0,3,"|"));
      points.add(new Point(5,3,"|"));
      points.add(new Point(1,4,"-"));
      points.add(new Point(2,4,"-"));
      points.add(new Point(3,4,"-"));
      points.add(new Point(4,4,"-"));
      points.add(new Point(5,4,"-"));

    Result formatter = new Result(true,points,"Test");

    System.out.print("Test Canvas-->\n" + formatter.toString());

      return points;
  }
}
