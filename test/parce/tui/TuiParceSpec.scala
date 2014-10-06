package parce.tui

import org.specs2.mutable.Specification
import play.api.test.Helpers._
import play.api.test.{WithServer, TestServer}
import ru.isaev.tour4me.services.TuiService
import scala.concurrent.ExecutionContext.Implicits._

/**
 * Created by lerrox on 08.05.14.
 */
class TuiParceSpec extends Specification with TuiService{

  "Парсер туи" should {

    "должен парсить ж)" in new WithServer {

//      val result = await(importData(CITY_MOSCOW))
//      val hotels = result.map(p=>p.Hotels.map(h=>(h,p.Placement.CountryName))).flatten
//      val preps:List[Preposition] = result.map(p=> {val r:models.Preposition = p; r})
     // val grouped = hotels.groupBy(x => {x._1.HotelId})
      val fsd = 123

    }

  }

}
