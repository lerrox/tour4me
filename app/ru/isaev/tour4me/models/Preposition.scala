package ru.isaev.tour4me.models


import org.joda.time.DateTime
import scala.slick.driver.PostgresDriver.simple._
import scala.Some
import slick.lifted.{Tag, TableQuery, AbstractTable}
import scala.slick.driver.PostgresDriver.simple._
import slick.direct.AnnotationMapper.column
import slick.ast.ColumnOption.PrimaryKey

//import slick.model.Table

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
                        fromDate:DateTime,
                        tillDate:Option[DateTime],
                        accomodation:Option[String],
                        foodService:Option[String],
                        tourOperator:String
                        )
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

class Prepositions(tag:Tag) extends AbstractTable[Preposition](tag, Some("tours"), "tours"){
  def id = column[Option[Long]]("id", PrimaryKey)
  def hotelId = column[Long]("hotel_id")
  def price = column[Double]("price")
  def fromDate = column[DateTime]("from_date")
  def tillDate = column[Option[DateTime]]("till_date")
  def accomodation = column[Option[String]]("accomodation")
  def foodService = column[Option[String]]("food_service")
  def tourOperator = column[String]("tour_operator")
  def * = (id, hotelId, price, fromDate, tillDate, accomodation, foodService, tourOperator) <> ((Preposition.apply).tupled, Preposition.unapply)
}



