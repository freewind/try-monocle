package lens

object TryLensWithSeq extends App {

  case class Street(name: String)
  case class Address(street: Street)
  case class Company(addresses: Seq[Address])
  case class Employee(company: Company)

  val employee = Employee(Company(Seq(Address(Street("aaa street")), Address(Street("bbb street")), Address(Street("bpp street")))))

  // I want to capitalize the streets start with "b" only

  val modified = employee.copy(company = employee.company.copy(addresses = employee.company.addresses.map { address =>
    address.copy(street = address.street.copy(name = {
      if (address.street.name.startsWith("b")) {
        address.street.name.capitalize
      } else {
        address.street.name
      }
    }))
  }))

  println(modified)

  // FIXME how to make it better?
}
