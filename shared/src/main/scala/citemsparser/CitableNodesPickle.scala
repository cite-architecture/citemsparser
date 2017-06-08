package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

case class CitableNodePickle(URN: String, text: String, previous: Option[String], next: Option[String], sequence: Int ) {

  /** Create [[CitableNode]] object from pickled source
  */
  def citableNode: CitableNode = {
    CitableNode(CtsUrn(URN), text)
  }

  /** Create optional next URN from optional String.
  */
  def nextUrn: Option[CtsUrn] = {
    next match {
      case None => None
      case strOpt: Some[String] => Some(CtsUrn(strOpt.get))
    }
  }


  /** Create optional previous URN from optional String.
  */
  def previousUrn: Option[CtsUrn] = {
    previous match {
      case None => None
      case strOpt: Some[String] => Some(CtsUrn(strOpt.get))
    }
  }
}

case class CitableNodesPickle(Nodes: Vector[CitableNodePickle] ) {

  /** Create a Vector of [[CitableNode]]s from pickle data.
  */
  def citableNodes: Vector[CitableNode]= {
    Nodes.map(_.citableNode)
  }

  /** Create an OHCO2-model [[Corpus]] object
  * from pickle data.
  */
  def corpus: Corpus = {
    Corpus(citableNodes)
  }
}
