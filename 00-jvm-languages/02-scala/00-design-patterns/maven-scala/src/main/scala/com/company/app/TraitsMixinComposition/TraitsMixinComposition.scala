package com.company.app.TraitsMixinComposition

trait Cell {
  def save(x:Int)
  def retrieve
}

class StandardCell(protected var state:Int) extends Cell {
  override def save(x: Int) = state = x

  override def retrieve = state
}

trait Similar {
  def isSimilar(x:Int): Boolean
}

class TraitsMixinComposition {

}


