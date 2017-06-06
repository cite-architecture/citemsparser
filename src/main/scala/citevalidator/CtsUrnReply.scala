package edu.holycross.shot.citevalidator

import edu.holycross.shot.cite._


/** Results of running a test.
*
* @param testId Test run.
* @param success True if test succeeded.
* @param message Human-readable message.
*/
case class CtsUrnReply(testId: Cite2Urn, success: Boolean, message: String) {
}
