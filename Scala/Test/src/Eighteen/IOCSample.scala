package Eighteen

/**
  * 依赖注入
  */
class IOCSample {

  //Log trait
  trait LogComponent{
    trait Logger{
      def log(msg:String)
    }

    //abstract,扩展类实现
    val logger:Logger

    class FileLogger(file:String) extends Logger{
      override def log(log: String): Unit = println(log)
    }
  }


  //Auth trait
  trait AuthComponent{
    /**
      * 只能混入在LogComponent扩展类
      * 具备log功能
      */
    this:LogComponent=>

    trait Auth{
      def login(name:String)
    }

    //abstract，扩展类实现
    val auth:Auth

    class MockAuth(val file:String) extends Auth{
      override def login(name: String): Unit = {
        println(name)
      }
    }
  }


  //object
  object AppComponent extends LogComponent with AuthComponent{
    //注入log功能
    val logger = new FileLogger("zach")
    //注入auth功能
    val auth = new MockAuth("zachLogin")
  }
}
