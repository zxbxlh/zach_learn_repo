package Eighteen

class EighteenTest {

}

object EighteenTest{
  def apply: EighteenTest = new EighteenTest()

  def test() = {
//    val book = new Book
//    book.setContent("zach").setTitle("zach's book")
//    println(book)

    val network = new Network
    network.test()
  }
}
