package lens

import com.softwaremill.quicklens._
import language.higherKinds

object TryQuicklens extends App {

  case class Street(name: String)
  case class Address(street: Street)
  case class Company(address: Seq[Address])
  case class Employee(company: Company)

  def foo(e: Employee) = {
    modify(e)(_.company.address.each.street.name).using {
      case name if name.startsWith("b") => name.capitalize
      case name => name
    }
  }

  val employee = Employee(Company(List(
    Address(Street("aaa string")),
    Address(Street("bbb string")),
    Address(Street("bpp string")))))


  val modified = foo(employee)
  println(modified)

}
