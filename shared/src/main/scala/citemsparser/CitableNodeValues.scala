package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._

case class CitableNodeValue(URN: String, text: String, previous: String, next: String, index: Int ) {}

case class CitableNodeValues(Nodes: Vector[CitableNodeValue] ) {
}
