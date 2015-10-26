package lens

object ScalazDemo extends App {

  case class Street(name: String)
  case class Address(street: Street)
  case class Company(addresses: Seq[Address])
  case class Employee(company: Company)

  val employee = Employee(Company(Seq(
    Address(Street("aaa street")),
    Address(Street("bbb street")),
    Address(Street("bpp street")))))

  def test(): Unit = {


    import scalaz.Lens
    import Lens._
    val employeeCompanyL = lensu[Employee, Company]((e, c) ⇒ e.copy(company = c), _.company)

    val companyAddressesL: Lens[Company, Seq[Address]] = lensu[Company, Seq[Address]]((c, x) ⇒ c.copy(addresses = x), _.addresses)

    val streetL = lensu[Address, Street]((a, st) ⇒ a.copy(street = st), _.street)
    val streetNameL = lensu[Street, String]((st, name) ⇒ st.copy(name = name), _.name)
    val addressStreetNameL = streetL >=> streetNameL
    val employeeCompanyAddressesL = employeeCompanyL >=> companyAddressesL

    val addresses = employeeCompanyAddressesL.get(employee) map { addr ⇒
      val name = addressStreetNameL.get(addr)
      if (name.startsWith("b")) addressStreetNameL.set(addr, name.capitalize) else addr
    }

    val e2 = employeeCompanyAddressesL.set(employee, addresses)


    println(e2)
  }

}
