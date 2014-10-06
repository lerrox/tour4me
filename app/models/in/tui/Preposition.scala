package ru.isaev.tour4me.models.in.tui

import org.joda.time.DateTime
import play.api.libs.json._

// JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

/**
 * Created by lerrox on 07.05.14.
 */
case class Preposition(Hotels: List[Hotel],
                       Rating:Rating,
                       Placement:Placement,
                       Accomodation:Accomodation,
                       FromTo:FromTo,
                       PriceKey: Long,
                       Price: Double,
                       PriceCurrency: String,
                       ExcursionTourNameId: Option[Long],
                       ExcursionTourName: Option[String],
                       ExcursionTourUrl: Option[String],
                       Photos: List[String],
                       OffersCount: Int,
                       QuotaHotel: Int,
                       QuotaFlightTo: Int,
                       QuotaFlightFrom: Int,
                       IsExcursionTourGroup: Option[Boolean]
                       )

object Preposition{

  implicit val prepReads: Reads[Preposition] = (
      (__ \ "Hotels").lazyRead(Reads.list[Hotel](Hotel.hotelReads)) and
      (__).read[Rating] and
      (__).read[Placement] and
      (__).read[Accomodation] and
      (__).read[FromTo] and
      (__ \ "PriceKey").read[Long] and
      (__ \ "Price").read[Double] and
      (__ \ "PriceCurrency").read[String] and
      (__ \ "ExcursionTourNameId").readNullable[Long] and
      (__ \ "ExcursionTourName").readNullable[String] and
      (__ \ "ExcursionTourUrl").readNullable[String] and
      (__ \ "Photos").read[List[String]] and
      (__ \ "OffersCount").read[Int] and
      (__ \ "QuotaHotel").read[Int] and
      (__ \ "QuotaFlightTo").read[Int] and
      (__ \ "QuotaFlightFrom").read[Int] and
      (__ \ "IsExcursionTourGroup").readNullable[Boolean]
    )(Preposition.apply _)
}

case class Rating(
                   TuiRating: Double,
                   TuiRatingCount: Int,
                   TripAdvisorRating: Int,
                   TripAdvisorRatingCount: Int,
                   TripAdvisorRatingImage: Option[String],
                   TripAdvisorHotelUrl: Option[String]

                   )

object Rating{
  implicit val reads: Reads[Rating] = (
      (__ \ "TuiRating").read[Double] and
      (__ \ "TuiRatingCount").read[Int] and
      (__ \ "TripAdvisorRating").read[Int] and
      (__ \ "TripAdvisorRatingCount").read[Int] and
      (__ \ "TripAdvisorRatingImage").readNullable[String] and
      (__ \ "TripAdvisorHotelUrl").readNullable[String]
    )(Rating.apply _)
}

case class Placement(
                      HotelName: String,
                      ResortName: String,
                      CityName: String,
                      DistrictName: String,
                      CountryId: Long,
                      CountryName: String,
                      HotelUrl: String,
                      Latitude: Double,
                      Longitude: Double
                      )

object Placement{
  implicit val reads: Reads[Placement] = (
    (__ \ "HotelName").read[String] and
      (__ \ "ResortName").read[String] and
      (__ \ "CityName").read[String] and
      (__ \ "DistrictName").read[String] and
      (__ \ "CountryId").read[Long] and
      (__ \ "CountryName").read[String] and
      (__ \ "HotelUrl").read[String] and
      (__ \ "Latitude").read[Double] and
      (__ \ "Longitude").read[Double]
    )(Placement.apply _)
}

case class Accomodation(TourTypeId: Long,
                        Adults: Int,
                        Children: Int,
                        Infants: Int,
                        HotelId: Long,
                        BookingUrl: String,
                        HotelCategory: String,
                        HotelCategoryName: String,
                        HotelPriority: Int,
                        PansionName: String,
                        NormalizedPansionName: String,
                        PansionCode: String,
                        RoomTypeName: String,
                        RoomCategoryName: String,
                        AccommodationType: String)

object Accomodation{
  implicit val reads: Reads[Accomodation] = (
    (__ \ "TourTypeId").read[Long] and
    (__ \ "Adults").read[Int] and
    (__ \ "Children").read[Int] and
    (__ \ "Infants").read[Int] and
    (__ \ "HotelId").read[Long] and
    (__ \ "BookingUrl").read[String] and
    (__ \ "HotelCategory").read[String] and
    (__ \ "HotelCategoryName").read[String] and
    (__ \ "HotelPriority").read[Int] and
    (__ \ "PansionName").read[String] and
    (__ \ "NormalizedPansionName").read[String] and
    (__ \ "PansionCode").read[String] and
    (__ \ "RoomTypeName").read[String] and
    (__ \ "RoomCategoryName").read[String] and
    (__ \ "AccommodationType").read[String]
    )(Accomodation.apply _)
}

case class FromTo(
                   DepartureDate: DateTime,
                   ArrivalDate: DateTime,
                   DepartureCityId: Long,
                   DepartureCityName: String,
                   Nights: Int,
                   Days: Int
                  )

object FromTo{
  implicit val reads: Reads[FromTo] = (
    (__ \ "DepartureDate").read[DateTime] and
    (__ \ "ArrivalDate").read[DateTime] and
    (__ \ "DepartureCityId").read[Long] and
    (__ \ "DepartureCityName").read[String] and
    (__ \ "Nights").read[Int] and
    (__ \ "Days").read[Int]
    )(FromTo.apply _)
}







