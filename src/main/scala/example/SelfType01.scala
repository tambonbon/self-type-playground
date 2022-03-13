package example

object SelfType01 {
  
  case class Edible()

  // let's say you have 2 separate type hierarchies
  // person hierarchy
  trait Person {
    def hasAllergiesFrom(thing: Edible): Boolean
  }
  trait Child extends Person
  trait Adult extends Person

  // diet hierarchy
  trait Diet {
    def eat(thing: Edible): Boolean
  }
  trait Carnivore extends Diet
  trait Vegetarian extends Diet

  // problem: you want the diet to be applicable to Persons only!
  // further, your functionality relies on logic from Person class
  // e.g. need a Person weight while you implement Diet API

  // Solutions:
  object Solutions {
    
    object Solution1_Inheritance {
      trait Diet extends Person
      // ---> all (non-private) Person functionality available to Diet
      // HOWEVER< this makes a mess out of two otherwise clean, separate hierarchies
    }

    object Solution2_Generics {
      trait Diet[P <: Person]
      // ---> Add a degree of separation by adding a type arguments
      // HOWEVER< Diet has access to Person type, but not its methods!
      // Scala3 resolves this ..
      // .. and enforces any Diet subclasses to pass a person as an argument
    }

    object Solution3_SelfTypes {
      trait Diet { self: Person => 
        // read as: whichever class implements Diet trait MUST ALSO implement Person trait
      } // // For example:
      trait Veggie extends Solution3_SelfTypes.Diet with Adult 
      // Veggie trait MUST extend BOTH Diet AND Adult (if no Person-related --> error)
      class VegAthlete extends Veggie {
        def hasAllergiesFrom(thing: Edible): Boolean = ???
      }
    }
  }
  
  /**
   * Remarks:
     - Inheritance is is-a (Dog is-a Animal)
     - Self-type is requires-a (Diet requires-a Person)
   */
  
}
