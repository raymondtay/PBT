
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary,Gen}
import org.scalacheck.Gen.{nonEmptyListOf, listOf, alphaStr, numChar}

/**
 * This is a specification which you have specified for
 * a fictitious `String` and `Math` objects. Take note that
 * the only framework here in place is the `ScalaCheck` library and nothing
 * else; so you can be sure there's no other 'magic' involved.
 *
 */
object StringSpecification extends Properties("String") {

  property("startsWith") = forAll { (a: String, b: String) =>
    (a+b).startsWith(a)
  }

  property("concatenate") = forAll { (a: String, b: String) =>
    if (a.isEmpty || b.isEmpty) true else (a+b).length > a.length && (a+b).length > b.length
  }

  property("substring") = forAll { (a: String, b: String, c: String) =>
    (a+b+c).substring(a.length, a.length+b.length) == b
  }

}

// This example illustrates the simple fact that property-based approach is
// suitable for deriving mathematical properties.
object MathProps extends Properties("Math") {

  property("max") = forAll { (x: Int, y: Int) =>
    val z = java.lang.Math.max(x, y)
    (z == x || z == y) && (z >= x && z >= y)
  }

  property("min") = forAll { (x: Int, y: Int) =>
    val z = java.lang.Math.min(x, y)
    (z == x || z == y) && (z <= x && z <= y)
  }

}

// This example illustrates the possibility of leveraging property-based
// approach to testing Java code :) interoperability is such a good thing!
//
object StringUtilsProps extends Properties("StringUtils") {
 
  // This property is slightly more special is that we want to provide an
  // "override" for generating arbitrary numbers but positive ones instead of
  // the default. Very often in Scala, we suppress the compiler's capabilities
  // by introducing another implicit value which is _closer_ in scope to the
  // intended use (that's how Scala devs do it, see `pi` below for this
  // example)
  property("truncate") = {
    implicit val pi: Arbitrary[Int] = Arbitrary(Gen.posNum[Int]) /* provided positive numbers for indices */

    forAll { (s: String, n: Int) =>
      val t = StringUtils.truncate(s, n)

      (s.length <= n && t == s) || (s.length > n && t == (s.take(n)+"..."))
    }
  }

  property("tokenize") =
    forAll(listOf(alphaStr), numChar) { (ts, d) =>
      val xs = ts.filterNot(_.isEmpty) /* Remove all empty strings */
      val str = xs.mkString(d.toString)
      StringUtils.tokenize(str, d).toList == xs
    }

  property("contains") =
    forAll { (s1: String, s2: String, s3: String) =>
      StringUtils.contains(s1+s2+s3, s2)
    }

}

