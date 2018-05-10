import java.sql.Timestamp

import com.seek.parser.FileParser
import org.scalatest.{FlatSpec, Matchers}

class ParserSpec extends FlatSpec with Matchers with Fixtures {

  "FileParser" should " throw error while parsing file from invalid path" in {
    FileParser.readFile(invalidFileName).isFailure should equal(true)
  }
  "FileParser" should " throw error while parsing file with invalid dataset" in {
    FileParser.readFile(invalidDataFileName).isFailure should equal(true)
  }
  "FileParser" should " throw error while parsing empty file" in {
    FileParser.readFile(emptyDataFileName).isFailure should equal(true)
  }
  "FileParser" should " read the file with valid data" in {
    FileParser.readFile(fileName).isSuccess should equal(true)
  }
  "FileParser" should " parse the timestamp string to Timestamp" in {
    FileParser.getTimestamp("2016-12-01T07:00:00").get.isInstanceOf[Timestamp] should equal(true)
  }

}
