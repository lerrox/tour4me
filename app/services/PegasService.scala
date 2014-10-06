package ru.isaev.tour4me.services

import scala.collection.JavaConversions._
import concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.Logger
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.util.Cookie


/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 05.10.14
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
trait PegasService {
    def getRecomendations() = {
      Future.successful{
        val url = "http://pegast.ru"
        val driver = new WebClient()
        driver.getCookieManager.addCookie(new Cookie("pegast.ru", "departureCity", "74"))
        try{
          val page = driver.getPage(url)
          Left(page)
        }
        catch {
          case e:Exception => Logger.logger.debug(e.getMessage)
          Right(e)
        }

      }

    }
}
//
//class PegasClient extends HtmlUnitDriver{
//  def getClient = {
//    this.getWebClient
//  }
//}


