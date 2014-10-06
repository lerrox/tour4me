package ru.isaev.tour4me.models.in.tui

import org.joda.time.DateTime
import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

/**
 * Created by lerrox on 07.05.14.
 */
case class Hotel(
                  HotelId: Long,
                  Nights: Int,
                  HotelCategory: String,
                  MealTypeKey: Int,
                  RoomTypeKey: Int,
                  RoomCategoryKey: Int,
                  HotelName: String,
                  ResortName: String,
                  CityName: String,
                  DistrictName: String,
                  PansionName: String,
                  RoomTypeName: String,
                  RoomCategoryName: String,
                  AccommodationType: String,
                  HotelUrl: String)



object Hotel{

  implicit val hotelReads: Reads[Hotel] = (
    (__ \ "HotelId").read[Long] and
    (__ \ "Nights").read[Int] and
    (__ \ "HotelCategory").read[String] and
    (__ \ "MealTypeKey").read[Int] and
    (__ \ "RoomTypeKey").read[Int] and
    (__ \ "RoomCategoryKey").read[Int] and
    (__ \ "HotelName").read[String] and
    (__ \ "ResortName").read[String] and
    (__ \ "CityName").read[String] and
    (__ \ "DistrictName").read[String] and
    (__ \ "PansionName").read[String] and
    (__ \ "RoomTypeName").read[String] and
    (__ \ "RoomCategoryName").read[String] and
    (__ \ "AccommodationType").read[String] and
    (__ \ "HotelUrl").read[String]
    ) (Hotel.apply _)

}
