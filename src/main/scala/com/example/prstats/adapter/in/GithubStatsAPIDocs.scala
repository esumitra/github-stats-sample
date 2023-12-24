package com.example.prstats.adapter.in
import sttp.tapir._
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import sttp.tapir.server.ServerEndpoint

object GithubStatsAPIDocs {

  val swaggerEndpoints: List[ServerEndpoint[Any, Future]] =
    SwaggerInterpreter().fromEndpoints[Future](GithubStatsRESTAPI.allAppRoutes, "Github Stats Summary", "1.0")
  
}
