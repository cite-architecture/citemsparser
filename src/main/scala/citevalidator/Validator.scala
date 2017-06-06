package edu.holycross.shot.citevalidator

import java.net.URL
import scala.io.Source

import net.liftweb.json._


/** Class to validate a CITE microservice at a given URL
* against standard tests.
*
* @param serviceUrl Location of the microservice instance.
*/
case class Validator(serviceUrl: URL) {

  /** Run tests defined in text available from  a given [[Source]].
  *
  * @param src Source text of test definitions in JSON format.
  */
  def validate(src: Source): Vector[TestResult] = {
    val json = parse(src.getLines.mkString("\n"))
    validate(json)
  }


  /** Run tests defined by specification in JSON format.
  *
  * @param src Source text of test definitions in JSON format.
  */
  def validate(jsonText: String): Vector[TestResult] = {
    val json = parse(jsonText)
    validate(json)
  }

  /** Run tests defined by specification in JSON format.
  *
  * @param json Source text of test definitions as parsed JSON object.
  */
  def validate(json: JValue) : Vector[TestResult] = {
    Vector[TestResult]()
  }
}
