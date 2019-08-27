
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.Gen.{listOf, alphaStr, numChar}

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
}

// This example illustrates the possibility of leveraging property-based
// approach to testing Java code :) interoperability is such a good thing!
//
object StringUtilsProps extends Properties("StringUtils") {
  
  property("truncate") = forAll { (s: String, n: Int) =>
    val t = StringUtils.truncate(s, n)
    (s.length <= n && t == s) || (s.length > n && t == s.take(n)+"...")
  }

  property("tokenize") = forAll(listOf(alphaStr), numChar) { (ts, d) =>
    val str = ts.mkString(d.toString)
    StringUtils.tokenize(str, d).toList == ts
  }

  property("contains") = forAll { (s1: String, s2: String, s3: String) => StringUtils.contains(s1+s2+s3, s2) }

}

