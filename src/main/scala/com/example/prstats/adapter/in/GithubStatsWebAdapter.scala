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

    def generateRepoSummary(name: Reponame): Future[Either[(StatusCode, ErrorInfo), RepoSummary]] = Future {
      val stats = useCase.repoSummary(name.reponame)
      RepoSummary(name.reponame, stats).asRight
    }

    List(
      GithubStatsRESTAPI.userSummary.serverLogic(generateUserSummary),
      GithubStatsRESTAPI.repoSummary.serverLogic(generateRepoSummary),
    )
  }

  def startServerWithEndpoints(port: Int = 8080, serverEndpoints: List[ServerEndpoint[Any, Future]]): Unit = {
    import akka.actor.ActorSystem
    import akka.http.scaladsl.Http
    import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
    import scala.concurrent.duration._

    implicit val actorSystem: ActorSystem = ActorSystem()
    import actorSystem.dispatcher
    val allRoutes =AkkaHttpServerInterpreter().toRoute( serverEndpoints ++ GithubStatsAPIDocs.swaggerEndpoints)
    val server = Http().newServerAt("0.0.0.0", port).bind(allRoutes)
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
