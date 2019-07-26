/**
  * test person class
  */
class Person (val name:String,private var age:Int,val country: Country.Country){
  println("name: "+name+",age: "+age+",country: "+country)

  //assist constructor
  def this(name:String){
    this(name,0,Country.China)
  }

  //age setter
  def age_(newValue:Int) = {
    this.age = age
  }

  //age getter
  def age_ = age

  def area() = Country.area(country)

}

object Person{
  private var country = "china"
  def updateCountry(newValue:String) = {country = newValue;country}

  def apply(name:String,age:Int,country: Country.Country):Person = new Person(name,age,country)
}
