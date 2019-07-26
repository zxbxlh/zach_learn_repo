package Eighteen

import javax.swing.JFrame

/**
  * 自身类型
  */
class SelfType {

  //base trait
  trait Logged{
    //abstract
    def log(msg:String)
  }

  //sub trait
  trait LoggedException extends Logged{
    //LoggedException只能被混入Exception的扩展类
    this:Exception =>
      override def log(msg: String): Unit = println(getMessage())
  }

  //error! JFrame don't extend Exception
  //val f = new JFrame with LoggedException
}
