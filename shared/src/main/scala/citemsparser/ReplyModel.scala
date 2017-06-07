package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._


object TextReplyType extends Enumeration {
  val UrnList, CitableNodeList, TextException = Value
}



trait CiteMsReply {
  def metadata: ReplyMetadata
}

case class UrnListReply(metadata: ReplyMetadata, data: String) extends CiteMsReply


case class CitableNodeListReply(metadata: ReplyMetadata, data: String) extends CiteMsReply
