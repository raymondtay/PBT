package sorting

import org.scalacheck._
import org.scalacheck.Properties
import org.scalacheck.Prop._
import Arbitrary.arbitrary
import _root_.scala.collection.immutable.{List => SList}

trait ConsDataGenerators {
  import Gen._ // bring in the data generators

  def genEmptyList = const(Nil())
  def someList(n: BigInt) = containerOfN[SList,BigInt](n.intValue, arbitrary[BigInt])

  def genSomeList : Gen[List] = for {
    n <- choose(2, 100)
    xs <- someList(n)
  } yield {
    InsertionSort.listBuilder(xs)
  } 
  implicit val arbitraryList = Arbitrary(genSomeList)
}

object InsertionSortProperties extends Properties("InsertionSort") with ConsDataGenerators {
  import InsertionSort.{head, tail, size, contents, isSorted, sort, min}

  property("Validating the sorting algorithm") = forAll { xs: List =>
    (!isSorted(xs)) ==> (sort(xs) != xs)
  }

  property("A sorted list will have the minimal element at the head") =
    forAll { xs: List =>
      val sxs = sort(xs)
      (head(sxs) <= head(tail(sxs))) ==> (min(sxs) match {
        case Some(value) => value <= head(sxs).intValue
        case None() => false
      })
    }

}

