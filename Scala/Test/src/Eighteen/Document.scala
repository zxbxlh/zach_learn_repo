package Eighteen

/**
  * 单例类型
  */
class Document {

  var title:String = ""
  var content:String = ""

  def setTitle(value:String):this.type =  {
    this.title = value
    this
  }

  override def toString: String = title+","+content

}
