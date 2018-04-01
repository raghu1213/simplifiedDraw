package com.practice.draw.test.dummy;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import com.practice.draw.utils.Point;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.HashSet;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;

@RunWith(Spectrum.class)
public class JavaTest {{
  feature("Collection test",()->{
    Variable<Collection<Point>> col = new Variable<>();
    given("A set",()->{
      col.set(new HashSet<Point>());
      col.get().add(new Point(0,0,"x"));

    });
    when("A new value with same coordinates inserted",()->{
      col.get().add(new Point(0,0,"y"));
    });
    then("Element in set is overwritten",()->{
      Assert.assertTrue(col.get().size()==2);
    });
  });
}
}
