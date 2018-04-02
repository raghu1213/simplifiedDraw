package com.practice.draw.test.command;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import com.practice.draw.test.TestHelperBase;
import com.practice.draw.command.LineCommand;
import com.practice.draw.utils.DefaultCoordinateGenerator;
import com.practice.draw.utils.Result;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static com.sun.javafx.fxml.expression.Expression.not;
import static org.hamcrest.core.Is.is;

@RunWith(Spectrum.class)
public class LineCommandTest extends TestHelperBase {{
    feature("Line Command",()-> {

        final Variable<LineCommand> command = new Variable<>();

        scenario("Draw a valid line", () -> {
            when("Command is valid", () -> {
                command.set(new LineCommand("L 1 1 9 1", new DefaultCoordinateGenerator()));
                printCommandString(command.get());

            });
            then("Draws a line", () -> {
                String expectedLine = "xxxxxxxxx\n";
                command.get().execute();
                Result result = command.get().getResults();
                Assert.assertThat(result.toString(), is(expectedLine));
                printResults(result);

            });
        });

        scenario("parameters  are not valid", () -> {
            when("Non integer values are supplied", () -> {
                command.set(new LineCommand("L a b 9 1", new DefaultCoordinateGenerator()));
                printCommandString(command.get());
            });
            then("Prints the invalid command message with its usage", () -> invalidCommandTest(command.get()));

            when("The command L with -ve x1", () -> {
                command.set(new LineCommand("L -1 1 9 1", new DefaultCoordinateGenerator()));
                printCommandString(command.get());
            });
            then("Prints invalid command message", () -> invalidCommandTest(command.get()));

            when("More arguments are supplied", () -> {
                command.set(new LineCommand("L 1 1 9 1 2", new DefaultCoordinateGenerator()));
                printCommandString(command.get());
            });
            then("Invalid command message is printed",()-> invalidCommandTest(command.get()));

            when("Less arguments are supplied", () -> {
                command.set(new LineCommand("L 1 1", new DefaultCoordinateGenerator()));
                printCommandString(command.get());
            });
            then("Invalid command message is printed",()-> invalidCommandTest(command.get()));

        });

    });

}



}
