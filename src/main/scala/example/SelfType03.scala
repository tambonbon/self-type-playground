package example

object SelfType03 {
  // Self-typed vs Inheritance
  // Problems with inheritance:
  // 1. Subclassing leaks the functionality of super class
  // 2. Inheritance breaks encapsulation!

  // Why inheritance is bad:
  trait Lambda {
    val l = "Lambda"
  }

  trait Calculus {
    this: Lambda => // --> when instantiate Calculus we have to provide Lambda
      val c = "Calculus"
      val lc = l + c
  }

  trait Turing {
    this: Calculus => // Turing only requires Calculus, not Lambda ..
      val t = "Turing"
      val lct = l + c + t // .. wrong, can't access l, because Turing can't access Lambda
      // good encapsulation!
      // if we had done trait Calculus extends Lambda, trait Turing extends Calculus..
      // .. then val lct would have been ok --> bad encapsulation!
  }

  val universe = new Turing with Calculus with Lambda {}
  println(universe.l) // as well as c, t
}

