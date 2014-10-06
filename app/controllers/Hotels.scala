package ru.isaev.tour4me.controllers

import play.api.mvc.{Action, Controller}

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 29.06.14
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
object Hotels extends Controller {

  def list = Action{
    Ok("отели")
  }

}
