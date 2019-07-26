package Trait

trait ShortLog extends Log {
   override def log(content: String){
    println("ShortLog: "+content)
    super.log(
      if(content.length>maxLength) content.substring(0,maxLength) else content
    )
  }
}
