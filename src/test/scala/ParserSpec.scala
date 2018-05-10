import java.sql.Timestamp

import com.seek.parser.FileParser
import org.scalatest.{FlatSpec, Matchers}

class ParserSpec extends FlatSpec with Matchers {

  val filePath: String = "/Users/baskaran/Personal/test_dataset.txt"
  val invalidFilePath: String = "sadasd"
  val invalidDataFilePath: String = "/Users/baskaran/Personal/invalid_test_dataset.txt"
  val emptyDataFilePath: String = "/Users/baskaran/Personal/empty_test_dataset.txt"

  "FileParser" should " throw error while parsing file from invalid path" in {
    FileParser.readFile(invalidFilePath).isFailure should equal(true)
  }
  "FileParser" should " throw error while parsing file with invalid dataset" in {
    FileParser.readFile(invalidDataFilePath).isFailure should equal(true)
  }
  "FileParser" should " throw error while parsing empty file" in {
    FileParser.readFile(emptyDataFilePath).isFailure should equal(true)
  }
  "FileParser" should " read the file with valid data" in {
    FileParser.readFile(filePath).isSuccess should equal(true)
  }
  "FileParser" should " parse the timestamp string to Timestamp" in {
    FileParser.getTimestamp("2016-12-01T07:00:00").get.isInstanceOf[Timestamp] should equal(true)
  }

}
