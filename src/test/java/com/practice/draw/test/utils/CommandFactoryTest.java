package com.practice.draw.test.utils;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import com.practice.draw.command.Command;
import com.practice.draw.utils.CommandFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static org.hamcrest.CoreMatchers.instanceOf;

@RunWith(Spectrum.class)
public class CommandFactoryTest {{
    feature("A command factory",()->{
      final Variable<Command> command = new Variable<>();
      scenario("Canvas command",()->{
        when("C command is run",()->{
           command.set( CommandFactory.createCommand("C 20 4"));
        });
        then("Canvas command is created",()->{
          Assert.assertThat(command.get(),instanceOf(Command.class));
        });
      });
    });
}
}
