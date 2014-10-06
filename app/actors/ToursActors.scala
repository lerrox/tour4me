package ru.isaev.tour4me.actors

import ru.isaev.tour4me.models.in.tui.Preposition
import ru.isaev.tour4me.services.TuiService
import scala.concurrent.duration._
import akka.actor.{TypedActor, TypedProps}
import akka.actor.TypedActor._
import scala.concurrent.{Await, Future}
import play.api.libs.concurrent.Akka._
import play.api.Play.current
import play.api.libs.concurrent.Akka

/**
 * Created by lerrox on 24.09.14.
 */
trait ToursActors {

  def getAt(index:Int):Future[Option[Preposition]]
  def apply(index:Int):Future[Option[Preposition]]
  def getPage(pageNum:Int, pageSize:Int):Future[List[Preposition]]

}

object TuiToursActor{
  lazy val actor:ToursActors = TypedActor(Akka.system).typedActorOf(TypedProps(classOf[ToursActors], new TuiToursActor()), "tuiToursActor")
}

class TuiToursActor extends ToursActors with TuiService {

  val tours:List[Preposition] = Await.result(getItemsForDeparture(CITY_KAZAN, 200), 20 seconds)

  def getAt(index: Int):Future[Option[Preposition]] = Future.successful{
    tours.lift(index)
  }

  def apply(index: Int):Future[Option[Preposition]] = {
    getAt(index)
  }

  def getPage(pageNum: Int, pageSize: Int):Future[List[Preposition]] = Future.successful{
    val from = (pageNum-1)*pageSize
    tours.slice(from, from+pageSize)
  }
}


