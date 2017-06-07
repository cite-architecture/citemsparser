package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._

import derive.key


case class ReplyMetadata(status: String, service: String, requestUrn: Option[String] = None) {




  def textReplyType : TextReplyType.Value = {
    status.toLowerCase match {
      case "exception" => TextReplyType.TextException
      case "success" => {
          service match {
            case "/texts/urns" => TextReplyType.UrnList
            case s: String => throw CiteMsException(s"Unrecognized or unimplemented value for service ${s}")
          }
      }
      case s: String => throw CiteMsException(s"Unrecognized value for reply status ${s}")
    }
  }
}
