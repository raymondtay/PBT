
sealed trait Person[+A] {
  val age : Int
  def isChild : Boolean = age < 14 /* According to Singapore Law */
  def isTeenager : Boolean = age >= 13 && age <= 19
}
case class Parent(name: String, age: Int) extends Person[String]
case class Child(name: String, age: Int, parent: Parent) extends Person[String]

// Examples of various "relationships"
object Relationships {
  def isChildOf(c: Child, p: Parent) : Boolean =
    if (c.parent.age < p.age) true else false
}


