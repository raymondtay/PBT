import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.pholser.junit.quickcheck.generator.InRange;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import java.util.*;

/**
 * This property test basically replaces the repetitive effort in [[MathTest]]
 * found in file: [[MathTest.java]] and the key insight here is to leverage data
 * generators for the heavy lifting of creating custom types and values;
 * leveraging the framework to run the permutations on the test's behalf.
 */
@RunWith(JUnitQuickcheck.class)
public class MathProperties {

  @Property public void maximumMustHold(@InRange(min="-10", max="10") int i) {
    int target = 10;
    int z = Math.max(i, target);
    assertEquals(z, target);
  }

  @Property public void minimumMustHold(@InRange(min="-10", max="10") int i) {
    int target = -11;
    int z = Math.min(i, target);
    assertEquals(z, target);
  }

}
