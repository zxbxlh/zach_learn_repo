class Dog(val name: String) extends Animal {
  var age = 0

  val cat = new Animal {
    val name: String = "cat"
    var age: Int = 0
  }

  override def printInfo(): Unit = super.printInfo()
}
