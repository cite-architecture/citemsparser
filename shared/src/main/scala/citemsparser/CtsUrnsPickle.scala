package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._

import derive.key


case class CtsUrnsPickle(URNs: Vector[String] ) {
  require(urns.size == URNs.size)

  def urns = {
    URNs.map(CtsUrn(_))
  }
}
