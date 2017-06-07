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
    "service": "/texts",
  	"Nodes": [{
  		"URN": "urn:cts:citeArch:groupA.work1.ed1:1.1",
  		"text": "Edition One. 1 point 1.",
  		"previous": "",
  		"next": "urn:cts:citeArch:groupA.work1.ed1:1.2",
  		"sequence": 1
  	},
  {
  "URN": "urn:cts:citeArch:groupA.work1.ed1:1.2",
  "text": "Edition One. 1 point 2.",
  "previous": "urn:cts:citeArch:groupA.work1.ed1:1.1",
  "next": "urn:cts:citeArch:groupA.work1.ed1:1.3",
  "sequence": 2
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

  it should "determine the reply type for requests to  /texts" in {
    val metadata = read[ReplyMetadata](textsReq1)
    assert(metadata.textReplyType == TextReplyType.UrnList)
  }

  it should "determine the reply type for requests to  /texts/{URN}" in {
    val metadata = read[ReplyMetadata](nodesReq1)
    assert(metadata.textReplyType == TextReplyType.CitableNodeList)
  }

  it should "determine the reply type for requests to  /texts/first" in {

      val firstReq1 = """
      {
      	"requestUrn": ["urn:cts:citeArch:groupA.work1.ed1:"],
      	"status": "Success",
        "service": "/texts/first",
      	"Nodes": [{
      		"URN": "urn:cts:citeArch:groupA.work1.ed1:1.1",
      		"text": "Edition One. 1 point 1.",
      		"previous": "",
      		"next": "urn:cts:citeArch:groupA.work1.ed1:1.2",
      		"sequence": 1
      	}]
      }
      """
      val metadata = read[ReplyMetadata](firstReq1)
      assert(metadata.textReplyType == TextReplyType.CitableNodeList)
  }

  it should "determine the reply type for requests to  /texts/last" in {

      val lastReq1 = """
      {
        "requestUrn": ["urn:cts:citeArch:groupA.work1.ed1:"],
        "status": "Success",
        "service": "/texts/last",
        "Nodes": [{
          "URN": "urn:cts:citeArch:groupA.work1.ed1:3.3",
          "text": "Edition One. 3 point 3.",
          "previous": "",
          "next": "urn:cts:citeArch:groupA.work1.ed1:3.2",
          "sequence": 9
        }]
      }
      """
      val metadata = read[ReplyMetadata](lastReq1)
      assert(metadata.textReplyType == TextReplyType.CitableNodeList)
  }

  it should "determine the reply type for requests to  /texts/next" in {
    val nextReq1 = """
    {
      "requestUrn": ["urn:cts:citeArch:groupA.work1.ed1:1.1"],
      "status": "Success",
      "service": "/texts/next",
      "Nodes": [{
        "URN": "urn:cts:citeArch:groupA.work1.ed1:1.2",
        "text": "Edition One. 1 point 2.",
        "previous": "",
        "next": "urn:cts:citeArch:groupA.work1.ed1:1.2",
        "sequence": 2
      }]
    }
    """
    val metadata = read[ReplyMetadata](nextReq1)
    assert(metadata.textReplyType == TextReplyType.CitableNodeList)
  }

  it should "determine the reply type for requests to  /texts/next producing empty lists" in {
    val nextReq2= """
    {
      "requestUrn": ["urn:cts:citeArch:groupA.work1.ed1:3.3"],
      "status": "Success",
      "service": "/texts/next",
      "Nodes": []
    }
    """
    val metadata = read[ReplyMetadata](nextReq2)
    assert(metadata.textReplyType == TextReplyType.CitableNodeList)
  }


  it should "determine the reply type for requests to  /texts/previous" in {
    val prevReq1 = """
    {
      "requestUrn": ["urn:cts:citeArch:groupA.work1.ed1:1.2"],
      "status": "Success",
      "service": "/texts/previous",
      "Nodes": [{
        "URN": "urn:cts:citeArch:groupA.work1.ed1:1.1",
        "text": "Edition One. 1 point 1.",
        "previous": "",
        "next": "urn:cts:citeArch:groupA.work1.ed1:1.2",
        "sequence": 1
      }]
    }
    """
    val metadata = read[ReplyMetadata](prevReq1)
    assert(metadata.textReplyType == TextReplyType.CitableNodeList)
  }

  it should "determine the reply type for requests to  /texts/previous producing empty lists" in {
    val prevReq2= """
    {
      "requestUrn": ["urn:cts:citeArch:groupA.work1.ed1:1.1"],
      "status": "Success",
      "service": "/texts/previous",
      "Nodes": []
    }
    """
    val metadata = read[ReplyMetadata](prevReq2)
    assert(metadata.textReplyType == TextReplyType.CitableNodeList)
  }


  it should "determine the reply type for requests to  /texts/urns" in {
    val urnReq1 = """{
  "requestUrn": ["urn:cts:citeArch:groupA.work1.ed1:1"],
  "status": "Success",
  "service": "/texts/urns",
  "URNs": [
  "urn:cts:citeArch:groupA.work1.ed1:1.1",
  "urn:cts:citeArch:groupA.work1.ed1:1.2",
  "urn:cts:citeArch:groupA.work1.ed1:1.3"
  ]
  }"""

  val metadata = read[ReplyMetadata](urnReq1)
  assert(metadata.textReplyType == TextReplyType.UrnList)
}
}
