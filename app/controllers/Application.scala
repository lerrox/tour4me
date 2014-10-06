package ru.isaev.tour4me.controllers

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import ru.isaev.tour4me.actors.TuiToursActor

object Application extends Controller {

  def index = Action.async {

      TuiToursActor.actor.getPage(1, 20).map{
        tours =>
          Ok(views.html.index("Tui Корстон", tours))
      }

  }

}