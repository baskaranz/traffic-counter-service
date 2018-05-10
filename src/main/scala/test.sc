import java.sql.Timestamp

case class CarEntry(str: String, count: Int)
val list = List(CarEntry("a", 1), CarEntry("a", 9), CarEntry("b", 2))

val res = list.groupBy(_.str).map(f => CarEntry(f._1, f._2.map(_.count).sum))