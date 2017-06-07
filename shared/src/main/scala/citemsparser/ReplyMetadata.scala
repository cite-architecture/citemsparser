package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._


/** Metadata for any reply in CITE microservices.
*
* @param status One of two literal strings, "exception" or "success".
* @param service Name of the requested service,
* @param requestUrn Optional string value for an included URN value.
*/
case class ReplyMetadata(status: String, service: String, requestUrn: Option[String]) {
  require(textsRequestNames.contains(service), s"Unrecognized service ${service}")

  /** Convert optional string value to optional
  * URN object.
  */
  def urn: Option[Urn] = {
    requestUrn match {
      case None => None
      case strOpt: Option[String] =>
      val s = strOpt.get
      if (s.contains("urn:cts")) {
        Some(CtsUrn(s))
      } else {
        Some(Cite2Urn(s))
      }
    }
  }

  /** Determine [[TextReplyType]] of the reply.
  */
  def textReplyType : TextReplyType.Value = {
    status.toLowerCase match {
      case "exception" => TextReplyType.TextException
      case "success" => {
          service match {
            case "/texts" => {
              requestUrn match {
                case None => TextReplyType.UrnList
                case strOpt: Some[String] => TextReplyType.CitableNodeList
              }


            }
            case "/texts/first" => TextReplyType.CitableNodeList
            case "/texts/last" => TextReplyType.CitableNodeList
            case "/texts/next" => TextReplyType.CitableNodeList
            case "/texts/previous" => TextReplyType.CitableNodeList
            case "/texts/urns" => TextReplyType.UrnList
            
            case s: String => throw CiteMsException(s"Unrecognized or unimplemented value for service ${s}")
          }
      }
      case s: String => throw CiteMsException(s"Unrecognized value for reply status ${s}")
    }
  }
}
