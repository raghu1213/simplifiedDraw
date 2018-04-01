package com.practice.draw.command;

import com.practice.draw.utils.Result;

public class QuitCommand implements Command {
  @Override
  public void execute() {
    System.exit(0);
  }

  @Override
  public Result getResults() {
    return null;
  }

  @Override
  public String getCommandString() {
    return null;
  }
}
