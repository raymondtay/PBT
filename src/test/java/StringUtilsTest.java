import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(JUnit4.class)
public class StringUtilsTest {

  @Test
  public void testTruncateShortString() {
    String s = StringUtils.truncate("abc", 5);
    assertEquals("abc", s);
  }
  @Test
  public void testTruncateLongString() {
    String s = StringUtils.truncate("Hello World", 8);
    assertEquals("Hello Wo...", s);
  }
  @Test
  public void testTokenize() {
    String[] tokens = StringUtils.tokenize( "foo;bar;42", ';');
    String[] expected = { "foo", "bar", "42" };
    assertTrue(java.util.Arrays.equals(tokens, expected));
  }
  @Test
  public void testTokenizeSingle() {
    String[] tokens = StringUtils.tokenize( "Hello World", ',');
    String[] expected = { "Hello World" };
    assertTrue(java.util.Arrays.equals(tokens, expected));
  }
  @Test
  public void testContainsTrue() {
    assertTrue(StringUtils.contains("abc", "bc"));
  }
  @Test
  public void testContainsFalse() {
    assertFalse(StringUtils.contains("abc", "42"));
  }

}

