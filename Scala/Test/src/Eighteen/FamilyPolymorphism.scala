package Eighteen

import scala.collection.mutable.ArrayBuffer

/**
  * 家族多态
  */
class FamilyPolymorphism {

  /**
    *
    * @tparam E 抽象类型
    */
  trait Listener[E] {
    def occurred(e: E): Unit
  }

  /**
    *
    * @tparam E 抽象类型1
    * @tparam L 抽象类型2，须扩展Listener[E]
    */
  trait Source[E, L <: Listener[E]] {

    private val listeners = new ArrayBuffer[L]

    def add(listener: L) = listeners += listener

    def remove(listener: L) = listeners -= listener

    def fire(event: E): Unit = {
      for (li <- listeners)
        li.occurred(event)
    }
  }


  /**
    * event
    */
  class ActionEvent {
    def onAction(): Unit = {
      println("ActionEvent:onAction()")
    }
  }

  /**
    * extend trait
    */
  trait ActionListener extends Listener[ActionEvent]{
    override def occurred(e: ActionEvent): Unit = {
      e.onAction()
    }
  }

  class Button extends Source[ActionEvent,ActionListener]{
    //trigger
    def click(): Unit ={
      fire(new ActionEvent())
    }
  }
}
