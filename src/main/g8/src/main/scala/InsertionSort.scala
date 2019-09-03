package sorting

sealed abstract class List
case class Cons(head:Int,tail:List) extends List
case class Nil() extends List

sealed abstract class OptInt
case class Some(value: Int) extends OptInt
case class None() extends OptInt

object InsertionSort {

  def listBuilder(elements: _root_.scala.collection.immutable.List[BigInt]) : List =
    elements match {
      case _root_.scala.collection.immutable.Nil => Nil()
      case x :: t => Cons(x.intValue, listBuilder(t))
    }

  def sortedIns(e: Int, l: List): List = {
    require(isSorted(l))
    l match {
      case Nil() => Cons(e,Nil())
      case Cons(x,xs) => if (x <= e) Cons(x,sortedIns(e, xs)) else Cons(e, l)
    }
  } ensuring(res => contents(res) == contents(l) ++ Set(e)
                    && isSorted(res)
                    && size(res) == size(l) + 1
            )

  def tail(l : List) : List = l match {
    case Nil() => Nil()
    case Cons(x, xs) => xs
  }

  def head(l : List) : BigInt = l match {
    case Nil() => BigInt(-1)
    case Cons(x, xs) => x
  }

  def size(l : List) : BigInt = (l match {
    case Nil() => BigInt(0)
    case Cons(_, xs) => 1 + size(xs)
  }) ensuring(_ >= 0)

  def contents(l: List): Set[Int] = l match {
    case Nil() => Set.empty
    case Cons(x,xs) => contents(xs) ++ Set(x)
  }

  def isSorted(l: List): Boolean = l match {
    case Nil() => true
    case Cons(x, Nil()) => true
    case Cons(x, Cons(y, ys)) => x <= y && isSorted(Cons(y, ys))
  }   

  def sort(l: List): List = (l match {
    case Nil() => Nil()
    case Cons(x,xs) => sortedIns(x, sort(xs))
  }) ensuring(res => contents(res) == contents(l)
                     && isSorted(res)
                     && size(res) == size(l))

  def min(l : List) : OptInt = l match {
    case Nil() => None()
    case Cons(x, xs) => min(xs) match {
      case None() => Some(x)
      case Some(x2) => if(x < x2) Some(x) else Some(x2)
    }
  }

}

