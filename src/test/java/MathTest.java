import junit.framework.TestCase;

public class MathTest extends TestCase {
  public void testMax1() {
    int z = Math.max(1,2);
    assertEquals(2, z);
  }

  public void testMax2() {
    int z = Math.max(1,0);
    assertEquals(1, z);
  }

  public void testMax3() {
    int z = Math.max(10,10);
    assertEquals(10, z);
  }

  public void testMax4() {
    int z = Math.max(-2,0);
    assertEquals(0, z);
  }

}

