package ru.isaev.tour4me.models


import org.joda.time.DateTime
import slick.lifted.{TableQuery, Tag}
import play.api.db.slick.Config.driver
import play.api.db.slick.Config.driver.simple._
import slick.ast.ColumnOption.PrimaryKey
import java.sql.Date

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 06.10.14
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
case class Preposition(id:Option[Long],
                       hotelId:Long,
                       price:Double,
                        fromDate:Date,
                        tillDate:Option[Date],
                        accomodation:Option[String],
                        foodService:Option[String],
                        tourOperator:String
                        )

class Prepositions(tag:Tag) extends Table[Preposition](tag, Some("tours"), "tours"){
  def id = column[Option[Long]]("id", PrimaryKey)
  def hotelId = column[Long]("hotel_id")
  def price = column[Double]("price")
  def fromDate = column[Date]("from_date")
  def tillDate = column[Option[Date]]("till_date")
  def accomodation = column[Option[String]]("accomodation")
  def foodService = column[Option[String]]("food_service")
  def tourOperator = column[String]("tour_operator")
  def * = (id, hotelId, price, fromDate, tillDate, accomodation, foodService, tourOperator) <> (Preposition.tupled, Preposition.unapply)
  def hotel = foreignKey("tours_hotel_fk",hotelId, Tables.hotels)(_.id.getOrElse(-1))
}

case class Hotel(id:Option[Long],
                 name:String,
                 city:String,
                 resort:Option[String],
                 stars:Option[Int],
                 mealType:Option[String],
                 districtName:Option[String],
                 hotelUrl:Option[String],
                 tourOperator:String,
                 extId:String,
                 tripAdvisorRating:Option[Int],
                 tripAdvisorCounter:Option[Int],
                 tripAdvisorUrl:Option[String]
                  )

class Hotels(tag:Tag) extends Table[Hotel](tag, Some("tours"), "hotels"){
  def id = column[Option[Long]]("id", PrimaryKey)
  def name = column[String]("name")
  def city = column[String]("city")
  def resort = column[Option[String]]("resort")
  def stars = column[Option[Int]]("stars")
  def mealType = column[Option[String]]("meal_type")
  def districtName = column[Option[String]]("district_name")
  def hotelUrl = column[Option[String]]("hotel_url")
  def tourOperator = column[String]("tour_operator")
  def extId = column[String]("ext_id")
  def tripAdvisorRating = column[Option[Int]]("trip_advisor_rating")
  def tripAdvisorCounter = column[Option[Int]]("trip_advisor_counter")
  def tripAdvisorUrl = column[Option[String]]("trip_advisor_url")
  def * = (id, name, city, resort, stars, mealType, districtName
    , hotelUrl, tourOperator, extId, tripAdvisorRating, tripAdvisorCounter, tripAdvisorUrl) <> (Hotel.tupled, Hotel.unapply)
}

object Tables{
  lazy val hotels = TableQuery[Hotels]
  lazy val prepositions = TableQuery[Prepositions]
}





