package Eighteen

import scala.collection.mutable.ArrayBuffer

/**
  * 类型投影
  */
class Network {

  //sub class
  class Member(val name:String){

    //val contacts = new ArrayBuffer[Member]()

    //使用类型投影
    val contacts = new ArrayBuffer[Network#Member]
  }

  private val members = new ArrayBuffer[Member]()

  def join(name:String) = {
    val m = new Member(name)
    members += m
    m
  }

  //test
  def test(): Unit ={
    val chatter = new Network
    val myFace = new Network

    //type: chatter.Member
    val zach = chatter.join("zach")
    //type: myFace.Member
    val xlh = myFace.join("xlh")

    //wrong,different type
    zach.contacts += xlh
  }
}
