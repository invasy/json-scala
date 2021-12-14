package dev.invasy

package object json {
  implicit class JsonConverter(o: Any) {
    def toJson: String = o match {
      case a: Map[_,_] => "{" + a.map({ case (k, v) => "\"" + escape(k.toString) + "\":" + v.toJson }).mkString(",") + "}"
      case a: Seq[_] => "[" + a.map(v => v.toJson).mkString(",") + "]"
      case a: Array[_] => "[" + a.map(v => v.toJson).mkString(",") + "]"
      case n @ (_:Boolean|_:Byte|_:Short|_:Int|_:Long|_:Float|_:Double|_:BigInt|_:BigDecimal) => n.toString
      case None | () => "null"
      case a @ _ => "\"" + escape(a.toString) + "\""
    }

    private def escape(s: String) : String = s.replaceAll("\"" , "\\\\\"")
  }
}
