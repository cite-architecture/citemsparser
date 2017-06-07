package edu.holycross.shot.citemsparser
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import upickle.default._



class CitableNodePickleSpec extends FlatSpec {

  "A CitableNodePickle" should "create an ohco2 node" in {
    val prevReq1 = """
    {
      "requestUrn": ["urn:cts:citeArch:groupA.work1.ed1:1.2"],
      "status": "Success",
      "service": "/texts/previous",
      "Nodes": [{
        "URN": "urn:cts:citeArch:groupA.work1.ed1:1.1",
        "text": "Edition One. 1 point 1.",
        "previous": [],
        "next": ["urn:cts:citeArch:groupA.work1.ed1:1.2"],
        "sequence": 1
      }]
    }
    """

    val cnp = read[CitableNodesPickle](prevReq1)
    val theNode = cnp.Nodes(0)
    println(theNode.citableNode)
  }

  it should "create an optional next urn" in pending
  it should "create an optional previous urn" in pending

  "A CitableNodesPickle vector" should "create an ohco2 corpus" in pending
  it should "create a vector of citable notes"
}
