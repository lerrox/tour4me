package ru.isaev.tour4me.services

import concurrent.Future
import play.api.libs.json.JsArray
import play.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WSRequestHolder
import ru.isaev.tour4me.models.in.tui.Preposition


/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 24.09.14
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */

trait RequestBuilder{
  def priceFrom(from:Int, to:Int) : RequestBuilder
  def adults(count:Int) : RequestBuilder
  def daysLong(from:Int, till:Int) : RequestBuilder
  def fromCity(index:Int) : RequestBuilder
  def get():Future[List[Preposition]]
}

class TuiRequestBuilder(url:WSRequestHolder) extends RequestBuilder{

  val CITY_KAZAN = 61232
  val CITY_MOSCOW = 34

  def adults(count:Int) = {
    new TuiRequestBuilder(url.withQueryString(("AdultCount", count.toString)))
  }

  def priceFrom(from:Int, to:Int) = {
    new TuiRequestBuilder(url.withQueryString(("PriceRanges", s"${from}_RUB"), ("PriceRanges", s"${to}_RUB"), ("currency", "RUB")))
  }

  def daysLong(from:Int, till:Int) = {
    new TuiRequestBuilder(url.withQueryString(("NightsFrom", from.toString), ("NightsTo", till.toString)))
  }

  def fromCity(index:Int) = {
    new TuiRequestBuilder(url.withQueryString(("DepartureCity", index.toString)))
  }

  def get(): Future[List[Preposition]] = {

    url.withQueryString(
    ("groupBy", "websearch") ,
    ("searchForm", "B2C")
    ).get().map{
      resp =>
        val json = resp.json
        val preps = (json \\ "SearchResultItems" headOption).get.asInstanceOf[JsArray].value
        preps.map(Preposition.prepReads.reads(_).fold(
          valid = {
            prep=>
              Some(prep)
          },
          invalid = {
            fds=>
              Logger.error("couldn't parce element "+ fds)
              None
          }
        )).toList.flatten
    }
  }

  private def byDefault(url: WSRequestHolder) = {
    val qs = url.queryString

  }
}
