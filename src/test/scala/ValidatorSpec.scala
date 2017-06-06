package edu.holycross.shot.citevalidator



import org.scalatest.FlatSpec

import java.net.URL
import scala.io.Source

import net.liftweb.json._

class ValidatorSpec extends FlatSpec {

  val madeUpURL = new URL("http://cite-architecture.github.io/TOTALLY/MADE/UP/SERVICE/URL")
  val validator = Validator(madeUpURL)

  "A Validator" should "be constructed from a URL" in {
    validator match {
      case v: Validator => assert(true)
      case _ => fail("Should have created a Validator")
    }
  }

  it should "validate against a test suite defined by parsed JSON" in pending/*

    val testSrc = Source.fromFile("src/test/resources/texts-tests-1.0.DRAFT.json")
    val json = parse(testSrc.getLines.mkString("\n"))
    println(json)
    val tests = validator.validate(json)
    println("TESTS: " + tests)
    //println(validator.validate(parse(jsonText)))
  }
*/
}
