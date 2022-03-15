package example

object SelfType02 {
  trait Lambda {
    val l = "Lambda"
  }

  trait Calculus {
    this: Lambda => // --> when instantiate Calculus we have to provide Lambda
      val c = "Calculus"
      val lc = l + c
  }

  val badUnivese = new Calculus {} // wrong
  val goodUniverse = new Calculus with Lambda {}
}
