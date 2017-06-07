package edu.holycross.shot.citemsparser
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._

import upickle.default._


/**
*/
class ReplyMetadataSpec extends FlatSpec {


  val textsReq1 = """{
      "status": "success",
      "service": "/texts",
      "requestUrn": [],
      "URNs": [
          "urn:cts:citeArch:groupA.work1.ed1:",
          "urn:cts:citeArch:groupA.work1.ed2:",
          "urn:cts:citeArch:groupA.work1.ed2.ex1:",
          "urn:cts:citeArch:groupA.work2.ed1:"
      ]
  }"""

  val nodesReq1 = """
  {
  	"requestUrn": ["urn:cts:citeArch:groupA.work1.ed1:1.1-1.2"],
  	"status": "Success",
    "service": "/texts/urn",
  	"Nodes": [{
  		"URN": "urn:cts:citeArch:groupA.work1.ed1:1.1",
  		"text": "Edition One. 1 point 1.",
  		"previous": "",
  		"next": "urn:cts:citeArch:groupA.work1.ed1:1.2",
  		"index": 1
  	},
  {
  "URN": "urn:cts:citeArch:groupA.work1.ed1:1.2",
  "text": "Edition One. 1 point 2.",
  "previous": "urn:cts:citeArch:groupA.work1.ed1:1.1",
  "next": "urn:cts:citeArch:groupA.work1.ed1:1.3",
  "index": 2
  }]
  }
  """


  "ReplyMetadata" should "be parsed from JSON strings" in {
    val metadata = read[ReplyMetadata](textsReq1)
    metadata match {
      case md: ReplyMetadata => assert(true)
      case _ => fail("Should have made ReplyMetadata")
    }
  }

  it should "find None for an optional URN when none is present"  in {
    val metadata = read[ReplyMetadata](textsReq1)
    metadata.urn match {
      case None => assert(true)
      case u: Some[Urn] => fail("should have found None")
    }
  }


  it should "find an optional URN when present"  in {
    val metadata = read[ReplyMetadata](nodesReq1)
    metadata.urn match {
      case u: Some[Urn]  => {
        u.get match {
          case c: CtsUrn => assert(true)
          case _ => fail("Should have found CtsUrn")
        }
      }
      case None  => fail("should have found Some Urn")
    }
  }

  it should "determine the reply type for requests to the text service" in {
    val metadata = read[ReplyMetadata](textsReq1)
    assert(metadata.textReplyType == TextReplyType.UrnList)
  }


}
