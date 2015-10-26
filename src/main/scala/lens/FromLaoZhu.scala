package lens

import monocle.macros.Lenses

object FromLaoZhu extends App {

  @Lenses("_") case class Street(name: String)
  @Lenses("_") case class Address(street: Street)
  @Lenses("_") case class Company(addresses: Seq[Address])
  @Lenses("_") case class Employee(company: Company)

  val employee = Employee(Company(Seq(
    Address(Street("aaa string")),
    Address(Street("bbb string")),
    Address(Street("bpp string")))))

  import Address._
  import Company._
  import Employee._
  import Street._

  def transformName(name: String) = if (name.startsWith("b")) name.toUpperCase else name
  def singleAddr(address: Address) = (_street composeLens _name).modify(transformName)(address)
  val converted = (_company composeLens _addresses).modify(_.map(singleAddr))(employee)
  println(converted)

}
