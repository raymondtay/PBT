import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

/** 
 *  Universally quantified properties represent one-halve of the property-based
 *  testing approach.
 *
 *  What is implicit here is the fact that the data
 *  generators are actually part of ScalaCheck and made available via Scala's
 *  "implicit mechanism".
 */
object UniversalProperties extends Properties("Examples of Universal Quantification") {

  property("Double reversing a list will equal the original list") =
    forAll { l: List[String] => l.reverse.reverse == l }

  property("concating two strings does not alter the order") =
    forAll { (s1: String, s2: String) =>
      (s1 + s2).endsWith(s2)
    }

}

