package abstract_types


/*
Generics versus abstract types

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/80cf41be-b139-4050-b579-300ba82b8a2e.xhtml

 */

// abstract type example
abstract class PrintData
abstract class PrintMaterial
abstract class PrintMedia
trait Printer {
  type Data <: PrintData
  type Material <: PrintMaterial
  type Media <: PrintMedia
  def print(data: Data, material: Material, media: Media) = {
    s"Printing $data with $material material on $media media"
  }
}

case class Paper() extends PrintMedia
case class Air() extends PrintMedia
case class Text() extends PrintData
case class Model() extends PrintData
case class Toner() extends PrintMaterial
case class Plastic() extends PrintMaterial

class LaserPrinter extends Printer {
  type Media = Paper
  type Data = Text
  type Material = Toner
}

class ThreeDPrinter extends Printer {
  type Media = Air
  type Data = Model
  type Material = Plastic
}

object AbstractTypesApp {
  def main(args: Array[String]) = {
    val laserPrinter = new LaserPrinter
    val threeDPrinter = new ThreeDPrinter

    println(laserPrinter.print(Text(), Toner(), Paper()))
    println(threeDPrinter.print(Model(), Plastic(), Air()))
  }
}
/*
Comparing with string: true
 */
