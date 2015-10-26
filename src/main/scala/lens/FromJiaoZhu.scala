package lens

import lens.TryLensWithSeq.{Street, Address, Company, Employee}

object FromJiaoZhu extends App {

  def changeEmployeeCompany(f: Company => Company): Employee => Employee =
    e => e.copy(company = f(e.company))

  def changeCompanyAddress(f: Address => Address): Company => Company =
    c => c.copy(addresses = c.addresses map f)

  def changeAddressStreet(f: Street => Street): Address => Address =
    a => a.copy(street = f(a.street))

  // 组合
  def changeEmployeeCompanyAddressStreet(f: Street => Street): Employee => Employee =
    changeEmployeeCompany(changeCompanyAddress(changeAddressStreet(f)))

  val employee = Employee(Company(List(
    Address(Street("aaa street")),
    Address(Street("bbb street")),
    Address(Street("bpp street")))))


  println(changeEmployeeCompanyAddressStreet(s => s.copy(name = if (s.name.startsWith("b")) s.name.toUpperCase else s.name))(employee))

}
