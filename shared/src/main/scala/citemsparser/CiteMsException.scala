package edu.holycross.shot.citemsparser


/** Exception thrown by a class in this library.
*/
case class CiteMsException(message: String = "", cause: Option[Throwable] = None) extends Exception(message) {
  cause.foreach(initCause)
}
