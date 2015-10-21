package lens

import monocle.macros.Lenses

object TryLensMacro extends App {

  @Lenses case class Street(name: String)
  @Lenses case class Address(street: Street)
  @Lenses case class Company(address: Address)
  @Lenses case class Employee(company: Company)

  val employee: Employee = Employee(Company(Address(Street("freewind street"))))

  def capitalize = (Employee.company composeLens Company.address composeLens Address.street composeLens Street.name).modify(_.capitalize)
  val modified = capitalize(employee)

  println(modified)

}
