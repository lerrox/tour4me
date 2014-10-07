package ru.isaev.tour4me.models

import org.joda.time.DateTime

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
