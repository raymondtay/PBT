import org.scalacheck.Properties
import org.scalacheck.Prop.{forAll, propBoolean}

/** 
 *  Conditionally quantified properties represent the other halve of the property-based
 *  testing approach; in addition to universally-quantified properties.
 */
object ConditionalProperties extends Properties("Examples of Conditional Quantification") {

  property("Capacity of created list should equal the desired capacity") = forAll { n: Int =>
    (n >= 0 && n < 10000) ==> (List.fill(n)("").length == n)
  }
}


