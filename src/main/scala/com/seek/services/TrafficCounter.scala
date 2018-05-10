package com.seek.services

import java.io.{File, PrintWriter}

import com.seek.model.CarEntry
import com.seek.parser.FileParser
import com.typesafe.scalalogging._

import scala.util.{Failure, Success}

object TrafficCounter extends LazyLogging {

  val outputFile: String = "output.txt"

  /** Function for calculating the number of cars seen in total in the input file.
    *  @return the no. of cars as Int
    */
  def getTotalNoOfCars(carEntries: List[CarEntry]): Int = {
      carEntries.map(_.count).sum
  }

  /** Function for calculating number of cars seen for all days listed in the input file.
    *  @return the list of car entries as List[CarEntry]
    */
  def getNoOfCarsInGivenDays(carEntries: List[CarEntry]): List[CarEntry] = {
    val datePattern = "yyyy-MM-dd"
    val carEntriesTimestampModified = carEntries.map(f => CarEntry(FileParser.getTimestamp(f.date.toString, datePattern).get, f.count))
    carEntriesTimestampModified.groupBy(_.date).map(f => CarEntry(f._1, f._2.map(_.count).sum)).toList
  }

  /** Function for number of cars seen for the 3 half hours that saw the most cars.
    *  @return the list of car entries as List[CarEntry]
    */
  def getTopNHalfHours(carEntries: List[CarEntry], n: Int): List[CarEntry] = {
    carEntries.sortBy(- _.count).take(n)
  }

  /** Function to generate the output for the all the scenario functions above reading a text file.
    *  @return Boolean to indicate the outputs were generated successfully or not.
    */
  def generateOutput(filePath: String): Boolean = {
    FileParser.readFile(filePath) match {
      case Success(carEntries) =>
        val writer = new PrintWriter(new File(outputFile))
        val nHalfHour = 3
        writer.println(s"No. of cars seen in total : ${getTotalNoOfCars(carEntries)}")
        writer.println(s"No. of cars seen in each day : ")
        getNoOfCarsInGivenDays(carEntries).foreach(f => writer.println(s"${f.date.toString.substring(0, f.date.toString.indexOf(" "))} ${f.count}"))
        writer.println(s"3 half hours that saw the most cars : ")
        getTopNHalfHours(carEntries, nHalfHour).
          foreach(f => writer.println(s"${f.date.toString.replace(" ", "T").substring(0, f.date.toString.indexOf("."))} ${f.count}"))
        writer.close()
        true
      case Failure(ex) =>
        logger.error("Generate output failed with exception ", ex)
        false
    }
  }

}
