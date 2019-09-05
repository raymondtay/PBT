import org.scalacheck._
import org.scalacheck.Properties
import org.scalacheck.Prop._
import Arbitrary.arbitrary

trait DataGenerators extends LowPriorityGenerators {

  def parentGenerator : Gen[Parent] =
    for {
      name <- arbitrary[String].suchThat(!_.isEmpty)
      age  <- Gen.choose(1, 100)
    } yield Parent(name, age)

  def childGenerator : Gen[Child] =
    for {
      n   <- arbitrary[String].suchThat(!_.isEmpty)
      age <- Gen.choose(1, 100)
      p   <- arbitrary[Parent]
    } yield Child(n, age, p)

  implicit val arbitraryParent : Arbitrary[Parent] = Arbitrary(parentGenerator)
  implicit val arbitraryChild : Arbitrary[Child] = Arbitrary(childGenerator)

}

trait LowPriorityGenerators {

  def parentGenerator2 : Gen[Parent] =
    for {
      name <- Gen.oneOf("Tom","Dick","Harry")
      age  <- Gen.choose(20, 100)
    } yield Parent(name, age)

  def childGenerator2 : Gen[Child] =
    for {
      n   <- arbitrary[String].suchThat(!_.isEmpty)
      age <- Gen.choose(1, 19)
      p   <- arbitrary[Parent]
    } yield Child(n, age, p)

  implicit val arbitraryParent2 : Arbitrary[Parent] = Arbitrary(parentGenerator2)
  implicit val arbitraryChild2 : Arbitrary[Child] = Arbitrary(childGenerator2)

}

/**
 * In the spirit of encapsulation/abstraction, it is desirable to separate the
 * data generators from the properties you are trying to validate
 */
object TeenagerProperties extends Properties("Teenager properties") with DataGenerators {

  property("A child should be below the age of 14") = Prop.forAll { p: Parent =>
    p.isChild == (p.age < 14)
  }

  property("A teenager should be between 13 and 19") = Prop.forAll { p: Child =>
    p.isTeenager == (p.age >= 13 && p.age <= 19)
  }
}

object ParentProperties extends Properties("Parent properties") with LowPriorityGenerators {
  property("Parent(s) must be older than the child") = {
    forAll { (c: Child, p: Parent) =>
      // Tip: Report all ages for relationships that failed the predicate
      // collect(c.age, p.age) { !Relationships.isChildOf(c,p) }
      (Relationships.isChildOf(c, p)) ==> c.age < p.age
    }
  }
}

/**
 * This properties illustrates a key concept in grouping properties
 */
object UProperties extends Properties("Combining Teenager/Parent properties") {
  include(TeenagerProperties)
  include(ParentProperties)
}
