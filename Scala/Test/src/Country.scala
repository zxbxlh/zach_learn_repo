object Country extends Enumeration {
  type Country = Value
  val China = Value("china")
  val American = Value("american")
  val HK = Value("hk")

  def area(country:Country):String = {
    if(country == China) "it is biggest"
    else if(country == American) "it is seconed"
    else "it is part of china"
  }
}
