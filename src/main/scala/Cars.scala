
sealed trait Vehicle[+A]
case class Car(axles: List[Axle], wheels: List[Wheel]) extends Vehicle[Car] {
  def isCar = (numOfAxles == 2) && (numOfWheels == 4)
  def numOfWheels = wheels.size
  def numOfAxles = axles.size
}


sealed trait Component[+A]
case class Axle() extends Component[Axle]
case class Wheel() extends Component[Wheel]

