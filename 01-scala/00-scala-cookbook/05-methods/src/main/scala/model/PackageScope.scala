package model

/*
mark method as private to the model package
private[model] def doX: Unit {}
 */
class PackageScope {
  private[model] def doX{
    println("super class package scope method")
  }
}

class Bar {
  val f = new PackageScope
  f.doX
}