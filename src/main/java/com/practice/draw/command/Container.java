package com.practice.draw.command;

import com.practice.draw.validator.Validator;

public interface Container {
   Validator getBoundaryValidator();
}
