import com.seek.parser.FileParser
import com.seek.services.TrafficCounter

import org.scalatest.{FlatSpec, Matchers}

import scala.util.{Failure, Success}

class TrafficCounterSpec extends FlatSpec with Matchers {

  val filePath: String = "/Users/baskaran/Personal/test_dataset.txt"

  "generateOutput " should " read the input file and generate the result in output file" in {
    TrafficCounter.generateOutput(filePath) should equal(true)
  }

  "getTotalNoOfCars " should " output the correct number" in {
    FileParser.readFile(filePath) match {
      case Success(carEntries) => TrafficCounter.getTotalNoOfCars(carEntries) should equal(398)
      case Failure(_) =>
    }
  }

  "getTopNHalfHours" should " output the correct number" in {
    val n = 3
    FileParser.readFile(filePath) match {
      case Success(carEntries) => TrafficCounter.getTopNHalfHours(carEntries, n).head.count should equal(46)
      case Failure(_) =>
    }
  }

  "getNoOfCarsInGivenDays " should " output the correct car entries" in {
    FileParser.readFile(filePath) match {
      case Success(carEntries) => TrafficCounter.getNoOfCarsInGivenDays(carEntries).size should equal(4)
      case Failure(_) =>
    }
  }

}
