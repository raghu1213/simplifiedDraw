package com.practice.draw.test.command;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import com.practice.draw.test.TestHelperBase;
import com.practice.draw.command.CanvasCommand;
import com.practice.draw.utils.DefaultCoordinateGenerator;
import com.practice.draw.utils.Result;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Spectrum.class)
public class CanvasCommandTest extends TestHelperBase {{

    feature("Canvas",()-> {
        final Variable<CanvasCommand> command = new Variable<>();

        scenario("Draw a valid Canvas",()->{
            when("The command is valid",()-> {
                command.set(new CanvasCommand("C 10 5", new DefaultCoordinateGenerator()));
                printCommandString(command.get());
            });
            then("Draws a canvas",()->{
                String expectedCanvas  =
                        "------------\n"+
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "------------\n";
                command.get().execute();
                Result result = command.get().getResults();

                Assert.assertTrue(result.isSuccessful());
                Assert.assertThat(result.toString(), is(expectedCanvas));

                printResults(result);
            });

        });

        scenario("Command arguments are invalid",()->{
            when("The arguments are not integer",()->{
                command.set(new CanvasCommand("C A B",null));
               printCommandString(command.get());
            });
            then("Gives and error with command usage",()->{
                command.get().execute();
                Result result = command.get().getResults();

                Assert.assertFalse(result.isSuccessful());
                printResults(result);
            });
        });



    });
}
}
