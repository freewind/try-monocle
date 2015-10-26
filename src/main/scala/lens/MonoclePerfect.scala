package lens

object MonoclePerfect extends App {

  import monocle.function.Each._
  import monocle.macros.Lenses
  import language.higherKinds

  @Lenses("_") case class Street(name: String)
  @Lenses("_") case class Address(street: Street)
  @Lenses("_") case class Company(addresses: List[Address])
  @Lenses("_") case class Employee(company: Company)

  object ILens {
    val employee = Employee(Company(List(
      Address(Street("aaa string")),
      Address(Street("bbb string")),
      Address(Street("bpp string")))))

    import monocle.std.list._

    import Employee._
    import Company._
    import Address._
    import Street._

    def main(args: Array[String]) {
      def transformName(name: String) = if (name.startsWith("b")) name.toUpperCase else name
      val converted = (_company composeTraversal (_addresses composeTraversal each) composeLens _street composeLens _name).modify(transformName)(employee)
      println(converted)
    }

  }

}
