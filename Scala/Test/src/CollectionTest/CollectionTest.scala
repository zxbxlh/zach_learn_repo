package CollectionTest

import scala.collection.SortedSet
import scala.collection.mutable.HashMap

class CollectionTest {

  def index(str: String) = {
    var map = new HashMap[Char, SortedSet[Int]]

    var i = 0
    for (c <- str) {
      map.get(c) match {
        case Some(s) => map(c) = s + i
        case None => map += (c -> SortedSet(i))
      }

      i += 1
    }

    map
  }

  def index2(str: String) = {
    var map = new HashMap[Char, Vector[Int]]()
    var i = 0

    for (ch <- str) {
      var cItem: Vector[Int] = map.getOrElse(ch, null)

      if (cItem == null) {
        map += (ch -> Vector(i))
      } else {
        map(ch) = cItem :+ i
      }

      i += 1
    }

    map
  }
}


object CollectionTest {
  def apply: CollectionTest = new CollectionTest()
}
