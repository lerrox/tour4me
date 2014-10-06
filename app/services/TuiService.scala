package ru.isaev.tour4me.services

import play.api.libs.ws.WS
import ru.isaev.tour4me.models.in.tui.Preposition
import scala.concurrent.Future
import play.api.libs.json.{JsArray, JsSuccess, Json}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.Logger
import com.google.common.util.concurrent.Futures
import play.api.Play.current

/**
 * Created by lerrox on 07.05.14.
 */
trait TuiService {

  val citiesCodes = Map("kazan"->"61232", "moscow"->"34")
  val CITY_KAZAN = "kazan"
  val CITY_MOSCOW = "moscow"
  val DEFAULT_CITY = "kazan"

  val DEFAULT_PAGE_SIZE = 500;

  def getItemsForDeparture(cityName:String, pageSize:Int = DEFAULT_PAGE_SIZE) ={
    val itemsCountFuture = getItemsCount(cityName)
    val items = itemsCountFuture.flatMap{count =>
        if(count <= pageSize) {
           importData(cityName)
        } else {
          val pages =  Math.ceil(count / pageSize).toInt + 2
           val futures = (1 until pages).map(i =>  importData(cityName, i, pageSize)).toList
           Future.sequence(futures).map(r => r.flatten)
        }
    }

    items
  }

  def getItemsCount(cityName:String) = {
    val request = WS.url("http://www.models.in.tui.ru/api-agency/search/GetFacetsAndTours?" +
      "AdultCount=2&DepartureCity="+citiesCodes.get(cityName).getOrElse(DEFAULT_CITY)+"&HotelCategories=2&NightsFrom=3&NightsTo=21" +
      "&PriceRanges=0_RUB&PriceRanges=9999999_RUB&QuotaFlight=Yes%7CRequest" +
      "&QuotaHotel=Yes%7CRequest&currency=RUB&currentDepartureCity=34&format=jsonnet" +
      "&groupBy=websearch&page=1&searchForm=B2B&sortBy=priority&pageSize=1&sortOrder=desc").get

    request.map{
      resp =>
        val json = resp.json
        val preps = (json \ "SearchResult" \ "TotalItems").asOpt[Int]
        preps.getOrElse(0)
    }
  }

  def importData(cityName:String = DEFAULT_CITY, page:Int = 1, pageSize:Int = DEFAULT_PAGE_SIZE):Future[List[Preposition]] = {
    val request = WS.url("http://www.models.in.tui.ru/api-agency/search/GetFacetsAndTours?" +
      "AdultCount=2&DepartureCity="+citiesCodes.get(cityName).getOrElse(DEFAULT_CITY)+"&HotelCategories=2&NightsFrom=3&NightsTo=21" +
      "&PriceRanges=0_RUB&PriceRanges=9999999_RUB&QuotaFlight=Yes%7CRequest" +
      "&QuotaHotel=Yes%7CRequest&currency=RUB&currentDepartureCity=34&format=jsonnet" +
      "&groupBy=websearch&page="+page+"&searchForm=B2B&sortBy=priority&pageSize="+pageSize+"&sortOrder=desc").get

    request.map{
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
}
