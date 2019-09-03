
import org.scalacheck._
import org.scalacheck.Properties
import org.scalacheck.Prop._
import Arbitrary.arbitrary

trait CarDataGenerators {

  /* 3/5 times it will generate 4 wheels */
  def wheelGenerator = Gen.frequency(
    (1, Gen.listOfN(1, Gen.const(Wheel()))),
    (3, Gen.listOfN(4, Gen.const(Wheel()))),
    (1, Gen.listOfN(2, Gen.const(Wheel())))
  )

  /* 3/5 times it will generate 2 axles */
  def axleGenerator  = Gen.frequency(
    (1, Gen.listOfN(1, Gen.const(Axle()))), 
    (3, Gen.listOfN(2, Gen.const(Axle()))), 
    (1, Gen.listOfN(4, Gen.const(Axle())))
  )

  def carGenerator = for {
    ws <- wheelGenerator
    as <- axleGenerator
  } yield Car(as, ws)

  implicit val arbitraryCar = Arbitrary(carGenerator)
}

object CarProperties extends Properties("All about Cars") with CarDataGenerators {

  property("A sedan car should have 2 axles & 4 wheels") =
    forAll { (car: Car) => 
      car.isCar ==> (car.numOfWheels == 4 && car.numOfAxles == 2)
    }

}

