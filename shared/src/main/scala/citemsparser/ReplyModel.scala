package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import upickle.default._

object TextReplyType extends Enumeration {
  val UrnList, CitableNodeList, TextException = Value
}



trait CiteMsReply {
  def metadata: ReplyMetadata
}

case class CtsUrnListReply(metadata: ReplyMetadata, data: String) extends CiteMsReply {

  def urns: Vector[CtsUrn] = {
    val pickle = read[CtsUrnsPickle](data)
    pickle.urns
  }
}


case class CitableNodeListReply(metadata: ReplyMetadata, data: String) extends CiteMsReply {

  def citableNodes: Vector[CitableNode] = {
    val pickle = read[CitableNodesPickle](data)
    pickle.citableNodes
  }

  def corpus: Corpus = {
    val pickle = read[CitableNodesPickle](data)
    pickle.corpus
  }
}


/** Factory object for creating specifically typed replies to `texts`
* microservice requests.
*/
object TextReply {
  def apply(replyText: String) : CiteMsReply = {
    val metadata = read[ReplyMetadata](replyText)

    metadata.textReplyType match {
      case  TextReplyType.TextException => throw CiteMsException("No, no, no.")
      case  TextReplyType.UrnList => CtsUrnListReply(metadata, replyText)
      case TextReplyType.CitableNodeList => CitableNodeListReply(metadata, replyText)
    }
  }
}
