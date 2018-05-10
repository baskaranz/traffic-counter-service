package com.seek.parser

import java.sql.Timestamp
import java.text.SimpleDateFormat
import com.typesafe.scalalogging._

import com.seek.model.CarEntry

import scala.io.Source
import scala.util.{Failure, Success, Try}

object FileParser extends LazyLogging {

  /** Read the given text file and output the object to be used for the functions to generate the outputs.
    *  @return  Try[[List[CarEntry]] object wrapped in a try.
    */
  def readFile(filepath: String): Try[List[CarEntry]] = Try {
    val bufferedSource = Source.fromFile(filepath)
    if (bufferedSource.isEmpty) {
      logger.warn("Input file is empty")
      throw new IllegalArgumentException
    } else {
      val carEntries: List[CarEntry] = bufferedSource.getLines.toList.flatMap { line =>
        val parsedLine = line.split(" ")
        val timeStampStr = parsedLine(0)
        val countStr = parsedLine(1)
        val maybeTimestampStr = getTimestamp(timeStampStr)
        val maybeCount = Try(countStr.toInt).toOption
        (maybeTimestampStr, maybeCount) match {
          case (Some(timestamp), Some(count)) =>  Some(CarEntry(timestamp, count))
          case _ => None
        }
      }
      bufferedSource.close
      carEntries
    }
  }

  /** Convert the text timestamp string into a Timestamp variable.
    *  @return the created `Timestamp` variable.
    */
  def getTimestamp(timeStampStr: String, pattern: String = "yyyy-MM-dd'T'HH:mm:ss") : Option[Timestamp] = timeStampStr match {
    case "" => None
    case _ => {
      val format = new SimpleDateFormat(pattern)
      Try(new Timestamp(format.parse(timeStampStr).getTime)) match {
        case Success(t) => Some(t)
        case Failure(ex) =>
          logger.error("Failed to parse timestamp string" , ex)
          None
      }
    }
  }
}
