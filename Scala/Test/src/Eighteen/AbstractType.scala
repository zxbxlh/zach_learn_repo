package Eighteen

import scala.io.Source


/**
  * 抽象类型
  */
class AbstractType {

  trait Reader{
    //抽象类型Contents，扩展类指定
    type Contents

    def read(file:String):Contents
  }

  trait StringReader extends Reader{
    //指定Contents为String类型
    type Contents = String

    def read(file:String) = Source.fromFile(file,"UTF-8").mkString
  }


  /**
    * 方式2
    * @tparam C 抽象类型
    */
  trait Reader2[C]{
    def read(file:String):C
  }

  trait StringReader2 extends Reader2[String]{
    def read(file:String) = Source.fromFile(file,"UTF-8").mkString
  }
}
