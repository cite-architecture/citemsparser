package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._


object TextReplyType extends Enumeration {
  val UrnList, CitableNodeList, TextException = Value
}





case class TextReplyModel(replyType: TextReplyType.Value, service: String) {
}


object TextReplyModel {


}
