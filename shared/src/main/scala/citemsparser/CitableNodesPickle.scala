package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._

case class CitableNodePickle(URN: String, text: String, previous: String, next: String, index: Int ) {
  
}

case class CitableNodesPickle(Nodes: Vector[CitableNodePickle] ) {
}
