package Eighteen

/**
  * 单例类型
  */
class Book extends Document {

  def setContent(value: String): this.type = {
    this.content = value; this
  }

  override def toString: String = super.toString

}
