package edu.holycross.shot.citemsparser
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import upickle.default._


/**
*/
class TextReplySpec extends FlatSpec {


  val urnsReq1 = """{
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


  "The TextReply factory object" should "make CtsUrnListReply objects for replies with URN lists" in {
    val replyObject = TextReply(urnsReq1)
    replyObject match {
      case u: CtsUrnListReply => assert(true)
      case _ => fail("Should have made CtsUrnListReply object")
    }
  }
  it should "make CitableNodeListReply objects for replies with lists of nodes" in {

    val replyObject = TextReply(nodesReq1)
    replyObject match {
      case u: CitableNodeListReply => assert(true)
      case _ => fail("Should have made CitableNodeListReply object")
    }
  }



}
