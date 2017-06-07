package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._


case class CtsUrnValues(URNs: Vector[String] ) {
  def urns = {
    URNs.map(CtsUrn(_))
  }
  require(urns.size == URNs.size)
}
