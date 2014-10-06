package parce.tui

import org.specs2.mutable.Specification
import play.api.test.WithServer
import play.api.test.Helpers._
import concurrent._
import concurrent.duration._
import ru.isaev.tour4me.services.TuiService
import scala.concurrent.ExecutionContext.Implicits._

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 29.06.14
 * Time: 17:54
 * To change this template use File | Settings | File Templates.
 */
class TuiBigCityParce extends Specification with TuiService{

  "Парсер туи" should {

    "должен парсить ж)" in new WithServer {

      val result = Await.result(getItemsForDeparture(CITY_KAZAN, 100), Duration(30, SECONDS))
      val fsd = 123

    }

  }

}