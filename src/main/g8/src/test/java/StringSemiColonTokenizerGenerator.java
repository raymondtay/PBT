import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.random.*;
import com.pholser.junit.quickcheck.generator.java.lang.StringGenerator;

public class StringSemiColonTokenizerGenerator extends StringGenerator {
  public StringSemiColonTokenizerGenerator() {}

  /**
   * Override this method to give a implementation on how to generate 
   * strings of random length and charsets. In this example, i am trying to
   * drive home the idea that :
   * (a) As a developer, you can define data generators without necessarily
   * adding more tests;
   * (b) Leveraging property-based testing approaches, you can create readable
   * and maintainable code.
   */
  @Override public String generate(SourceOfRandomness r, GenerationStatus status) {
    String[] items = {"", "a;", "a;b;", "a;b;f;f;"};
    return r.choose(items);
  }

}

