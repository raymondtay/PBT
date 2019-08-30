import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import java.util.*;

@RunWith(JUnitQuickcheck.class)
public class StringUtilProperties {

  @Property public void concatenationLength(String s1, String s2) {
    assertEquals(s1.length() + s2.length(), (s1 + s2).length());
  }
  
  @Property public void tokenizeBySemiColon(@From(StringSemiColonTokenizerGenerator.class) String s1) {
    HashSet<Integer> s = new HashSet();
    s.add(Integer.valueOf(0));s.add(Integer.valueOf(1));s.add(Integer.valueOf(2));s.add(Integer.valueOf(4));
    Integer foundLength = Integer.valueOf(StringUtils.tokenize(s1, ',').length);

    assertEquals(s.contains(foundLength), true);
  }

  @Property public void tokenizeByComma(@From(StringCommaTokenizerGenerator.class) String s1) {
    HashSet<Integer> s = new HashSet();
    s.add(Integer.valueOf(0));s.add(Integer.valueOf(1));s.add(Integer.valueOf(2));s.add(Integer.valueOf(4));
    Integer foundLength = Integer.valueOf(StringUtils.tokenize(s1, ',').length);

    assertEquals(s.contains(foundLength), true);
  }

}

