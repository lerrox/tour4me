import java.lang.RuntimeException
import play.api.{Application, mvc}
import play.api.mvc.Results._
import play.api.mvc.{Result, RequestHeader, WithFilters}
import play.Logger

/**
 * Created by lerrox on 29.04.14.
 */
object Global extends WithFilters(){


  override def onStart(app: Application): Unit = {
    super.onStart(app)
   // val tuiActor = TuiToursActor.actor
  }



}
