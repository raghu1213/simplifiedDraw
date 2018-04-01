package com.practice.draw.test.controller;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import com.practice.draw.test.TestHelperBase;
import com.practice.draw.command.Command;
import com.practice.draw.command.Container;
import com.practice.draw.command.Filler;
import com.practice.draw.controller.CommandController;
import com.practice.draw.controller.DefaultController;
import com.practice.draw.utils.CommandFactory;
import com.practice.draw.utils.Result;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.stream.Collectors;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Spectrum.class)
public class DefaultControllerTest extends TestHelperBase {{
    feature("Executes the commands",()->{
        CommandController controller = new DefaultController();
        Variable<Container> container = new Variable<>();
       
        scenario("Enters valid sequence of commands",()->{
            when("First command is canvas",()->{
                Command command = CommandFactory.createCommand("C 20 4");
                container.set((Container) command);
                printCommandString(command);
                controller.execute(command);
            });
            then ("Draws a canvas",()->{











                Assert.assertThat(controller.getResults().size(),is(1));
                String expectedResult = "----------------------\n" +
                                        "|                    |\n" +
                                        "|                    |\n" +
                                        "|                    |\n" +
                                        "|                    |\n" +
                                        "----------------------\n";
               Assert.assertThat(controller.getResults().get(0).toString(),is(expectedResult));
               printResults(controller.getResults().get(0));

            });

            when ("Line command is executed next",()-> {

                Command command = CommandFactory.createCommand("L 1 2 6 2");
                printCommandString(command);
                controller.execute(command);

            });
            then("Draws the canvas with line inside it",()->{
                Assert.assertThat(controller.getResults().size(),is(2));
                String expectedResult = "----------------------\n" +
                                        "|                    |\n" +
                                        "|xxxxxx              |\n" +
                                        "|                    |\n" +
                                        "|                    |\n" +
                                        "----------------------\n";
                Result result = new Result(true,controller.getResults()
                        .stream()
                        .flatMap(r->r.getPoints().stream())
                        .collect(Collectors.toList()),"Success" );
                Assert.assertThat(result.toString(),is(expectedResult));
                printResults(result);
            });

            when ("Another Line command is executed next",()-> {

                Command command = CommandFactory.createCommand("L 6 3 6 4");
                printCommandString(command);
                controller.execute(command);

            });
            then("Draws the canvas with two lines inside it",()->{
                Assert.assertThat(controller.getResults().size(),is(3));
                String expectedResult = "----------------------\n" +
                                        "|                    |\n" +
                                        "|xxxxxx              |\n" +
                                        "|     x              |\n" +
                                        "|     x              |\n" +
                                        "----------------------\n";
                Result result = new Result(true,controller.getResults()
                        .stream()
                        .flatMap(r->r.getPoints().stream())
                        .collect(Collectors.toList()),"Success" );
                Assert.assertThat(result.toString(),is(expectedResult));
                printResults(result);
            });

            when ("Rectangle command is executed next",()-> {

                Command command = CommandFactory.createCommand("R 14 1 18 3");
                printCommandString(command);
                controller.execute(command);

            });
            then("Updates the canvas with rectangle in it",()->{
                Assert.assertThat(controller.getResults().size(),is(4));
                String expectedResult = "----------------------\n" +
                                        "|             xxxxx  |\n" +
                                        "|xxxxxx       x   x  |\n" +
                                        "|     x       xxxxx  |\n" +
                                        "|     x              |\n" +
                                        "----------------------\n";
                Result result = new Result(true,controller.getResults()
                        .stream()
                        .flatMap(r->r.getPoints().stream())
                        .collect(Collectors.toList()),"Success" );
                Assert.assertThat(result.toString(),is(expectedResult));
                printResults(result);
            });

            when ("Bucket fill command is entered next with color o",()-> {

                Command command = CommandFactory.createCommand("B 10 3 o");
                printCommandString(command);

                ((Filler)command).setState(controller.getResults().stream()
                        .flatMap(r->r.getPoints().stream())
                        .collect(Collectors.toList()),container.get().getBoundary());

                controller.execute(command);

            });
            then("Updates the canvas with all shapes and fills it with color o",()->{

                Assert.assertThat(controller.getResults().size(),is(5));
                String expectedResult = "----------------------\n" +
                                        "|oooooooooooooxxxxxoo|\n" +
                                        "|xxxxxxooooooox   xoo|\n" +
                                        "|     xoooooooxxxxxoo|\n" +
                                        "|     xoooooooooooooo|\n" +
                                        "----------------------\n";
                Result result = new Result(true,controller.getResults()
                        .stream()
                        .flatMap(r->r.getPoints().stream())
                        .collect(Collectors.toList()),"Success" );
                Assert.assertThat(result.toString(),is(expectedResult));
                printResults(result);
            });
            when ("Again a bucket fill command given with different color",()-> {

                Command command = CommandFactory.createCommand("B 10 3 *");
                printCommandString(command);

                ((Filler)command).setState(controller.getResults().stream()
                        .flatMap(r->r.getPoints().stream())
                        .collect(Collectors.toList()),container.get().getBoundary());

                controller.execute(command);

            });
            then("Overwrites previous bucket fill with new color",()->{

                Assert.assertThat(controller.getResults().size(),is(6));
                String expectedResult = "----------------------\n" +
                        "|*************xxxxx**|\n" +
                        "|xxxxxx*******x   x**|\n" +
                        "|     x*******xxxxx**|\n" +
                        "|     x**************|\n" +
                        "----------------------\n";
                Result result = new Result(true,controller.getResults()
                        .stream()
                        .flatMap(r->r.getPoints().stream())
                        .collect(Collectors.toList()),"Success" );
                Assert.assertThat(result.toString(),is(expectedResult));
                printResults(result);
            });
            

        });
    });
}
}
