package Trait

trait WrapperLog extends Log {
   override def log(content: String): Unit = {
    println("WrapperLog: "+ content)
    super.log("NewWrapperLog: "+content)
  }
}
