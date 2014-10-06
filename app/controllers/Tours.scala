package ru.isaev.tour4me.controllers

import play.api.mvc.{Action, Controller}
import ru.isaev.tour4me.actors.TuiToursActor
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 29.06.14
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
object Tours extends Controller {

  def list = Action.async{
        TuiToursActor.actor.getPage(1, 20).map{
          tours =>
            Ok(views.html.tours.list("1", tours))
        }
  }

}
