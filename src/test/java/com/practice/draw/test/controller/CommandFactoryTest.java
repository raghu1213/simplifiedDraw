package com.practice.draw.test.controller;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import com.practice.draw.command.*;
import com.practice.draw.utils.CommandFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.feature;
import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.then;
import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.when;
import static org.hamcrest.CoreMatchers.instanceOf;

@RunWith(Spectrum.class)
public class CommandFactoryTest {
    {
        feature("Command creation", () -> {
            Variable<Command> command = new Variable<>();
            when("the command is C",()->{
                command.set(CommandFactory.createCommand("c"));
            });
            then("Canvas command is created",()->{
                Assert.assertThat(command.get(),instanceOf(CanvasCommand.class));
            });
            when("the command is R",()->{
                command.set(CommandFactory.createCommand("r" ));
            });
            then("Rectangle command is created",()->{
                Assert.assertThat(command.get(),instanceOf(RectangleCommand.class));
            });
            when("the command is L",()->{
                command.set(CommandFactory.createCommand("L"));
            });
            then("Line command is created",()->{
                Assert.assertThat(command.get(),instanceOf(LineCommand.class));
            });
            when("the command is B",()->{
                command.set(CommandFactory.createCommand("B"));
            });
            then("Fill command is created",()->{
                Assert.assertThat(command.get(),instanceOf(FillCommand.class));
            });
            when("the command is Q",()->{
                command.set(CommandFactory.createCommand("Q"));
            });
            then("Quit command is created",()->{
                Assert.assertThat(command.get(),instanceOf(QuitCommand.class));
            });
            when("the command is not supported",()->{
                command.set(CommandFactory.createCommand("wrong"));
            });
            then("Invalid command is created",()->{
                Assert.assertThat(command.get(),instanceOf(NotSupportedCommand.class));
            });

        });
    }}
