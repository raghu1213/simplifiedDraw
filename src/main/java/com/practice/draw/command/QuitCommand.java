package com.practice.draw.command;

import com.practice.draw.SupportedCommands;
import com.practice.draw.utils.Result;

public class QuitCommand extends CommandBase {
  public QuitCommand() {
    super(SupportedCommands.QUIT);
  }

  @Override
  public void execute() {
    System.exit(0);
  }

  @Override
  public Result getResults() {
    return null;
  }

}
