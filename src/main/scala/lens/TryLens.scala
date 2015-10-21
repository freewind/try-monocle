package lens

import monocle._
import monocle.function._
import monocle.macros.GenLens
import monocle.std.string._

object TryLens extends App {

  case class Street(name: String)
  case class Address(street: Street)
  case class Company(address: Address)
  case class Employee(company: Company)

  val employee: Employee = Employee(Company(Address(Street("freewind street"))))

  val modified = employee.copy(
    company = employee.company.copy(
      address = employee.company.address.copy(
        street = employee.company.address.street.copy(
          name = employee.company.address.street.name.capitalize))))

  println(modified)

  firstCreationMethod()

  secondCreationMethod()

  def secondCreationMethod(): Unit = {
    val _company = GenLens[Employee](_.company)
    val _address = GenLens[Company](_.address)
    val _street = GenLens[Address](_.street)
    val _name = GenLens[Street](_.name)

    val modified21 = (_company composeLens _address composeLens _street composeLens _name).modify(_.capitalize)(employee)
    println(modified21)

  }

  def firstCreationMethod(): Unit = {
    val _company = Lens[Employee, Company](_.company)(company => employee => employee.copy(company = company))
    val _address = Lens((_: Company).address)(address => company => company.copy(address = address))
    val _street = Lens[Address, Street](_.street)(street => address => address.copy(street = street))
    val _name = Lens[Street, String](_.name)(name=> street => street.copy(name = name))

    val modified11 = (_company composeLens _address composeLens _street composeLens _name).modify(_.capitalize)(employee)
    println(modified11)

    val modified12 = (_company ^|-> _address ^|-> _street ^|-> _name).modify(_.capitalize)(employee)
    println(modified12)

    val modified13 = (_company composeLens _address composeLens _street composeLens _name composeOptional headOption).modify(_.toUpper)(employee)
    println(modified13)
  }


}
