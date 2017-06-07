package edu.holycross.shot.citemsparser

import edu.holycross.shot.cite._


/** Results of running a test.
*
* @param testId Test run.
* @param request True if test succeeded.
* @param resultType Human-readable message.
*/
case class TestDefinition(testID: String, request: String, resultType: String) {
  override def toString = {
    s"${testID} ${request} (${resultType})"
  }
}
