package parce.pegas

import org.specs2.mutable.Specification
import play.api.test.WithServer
import concurrent.Await
import concurrent.duration._
import ru.isaev.tour4me.services.PegasService
import scala.concurrent.ExecutionContext.Implicits._

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 05.10.14
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
class PegasSpec extends Specification with PegasService {
  "Парсер пегас" should {

    "должен парсить ж)" in new WithServer {

      val result = Await.result(getRecomendations, Duration(30, SECONDS))
      val fsd = 123

    }

  }
}
