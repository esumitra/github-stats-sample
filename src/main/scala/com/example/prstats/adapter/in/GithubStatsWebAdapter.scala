package com.example.prstats.adapter.in

import scala.concurrent.Future
import com.example.prstats.application.port.in.PRSummaryUseCase
import sttp.tapir.server.ServerEndpoint
import cats.implicits._
import sttp.model.StatusCode
import com.typesafe.scalalogging.StrictLogging
import akka.http.scaladsl.server.Route

class GithubStatsWebAdapter(port: Int)(implicit val useCase: PRSummaryUseCase) extends StrictLogging {
  
  def serverEndpoints: List[ServerEndpoint[Any, Future]] = {
    import scala.concurrent.ExecutionContext.Implicits.global
    
    def generateUserSummary(name: Username): Future[Either[(StatusCode, ErrorInfo), UserSummary]] = Future {
      val stats = useCase.userSummary(name.username)
      UserSummary(name.username, stats).asRight
    }

    List(
      GithubStatsRESTAPI.userSummary.serverLogic(generateUserSummary)
    )
  }

  def startServerWithEndpoints(port: Int = 8080, serverEndpoints: List[ServerEndpoint[Any, Future]]): Unit = {
    import akka.actor.ActorSystem
    import akka.http.scaladsl.Http
    import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
    import scala.concurrent.duration._

    implicit val actorSystem: ActorSystem = ActorSystem()
    import actorSystem.dispatcher
    val routes: Route = AkkaHttpServerInterpreter().toRoute(serverEndpoints)
    val server = Http().newServerAt("0.0.0.0", port).bind(routes)
    server
      .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 10 seconds))
      .onComplete(_ => {
      logger.info(s"Server started at port ${port}")
    })
  }

  def startServer: Unit = {
    startServerWithEndpoints(port, serverEndpoints)
  }

}
