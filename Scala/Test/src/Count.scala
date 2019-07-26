import java.beans.BeanProperty

class Count {
  private var count = 0

  //default public
  @BeanProperty var name = ""

  //getter
  def currentCount = count;
  //setter
  def count_ (newValue:Int) = {
    count = newValue
  }

  //assist constructor1
  def this(name:String){
    //master constructor
    this()
    this.name = name
  }

  //assist constructor2
  def this(name:String,count:Int){
    this(name)
    this.count = count
  }

}
