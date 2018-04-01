package com.practice.draw.test.command;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import com.practice.draw.test.TestHelperBase;
import com.practice.draw.command.RectangleCommand;
import com.practice.draw.utils.DefaultCoordinateGenerator;
import com.practice.draw.utils.Result;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Spectrum.class)
public class RectangleTest extends TestHelperBase {{
  feature("Rectangle",()->{
    final Variable<RectangleCommand> command = new Variable<>();

    scenario("Draws a valid rectangle",()->{
      when("The command is valid",()->{
        command.set(new RectangleCommand("R 1 1 8 5",new DefaultCoordinateGenerator()));
        printCommandString(command.get());
      });
      then("draws a rectangle",()->{
        String expectedCanvas  =
              "xxxxxxxx\n"+
              "x      x\n" +
              "x      x\n" +
              "x      x\n" +
              "xxxxxxxx\n";
        command.get().execute();
        Result result = command.get().getResults();

        Assert.assertTrue(result.isSuccess());
        Assert.assertThat(result.toString(), is(expectedCanvas));
        printResults(result);
      });
    });
    scenario("Invalid command parameteres are provided",()->{
        when("Parameters are not integers",()->{
            command.set(new RectangleCommand("R a b c d", new DefaultCoordinateGenerator()));
            printCommandString(command.get());
        });
        then("Gives an error with usage",()->{
            invalidCommandTest(command.get());
        });

        when("Parameters are not unsigned integers",()->{
            command.set(new RectangleCommand("R -1 1 4 4", new DefaultCoordinateGenerator()));
            printCommandString(command.get());
        });
        then("Gives an error for command",()->{
            invalidCommandTest(command.get());
        });

    });
  });
}
}
