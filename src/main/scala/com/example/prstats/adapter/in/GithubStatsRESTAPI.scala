package com.example.prstats.adapter.in

import sttp.tapir._
import io.circe.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._

// input types
case class Username(username: String)
case class Reponame(reponame: String)

// output types
case class UserSummary (
  username: String,
  count: Long
)

case class RepoSummary (
  repositoryName: String,
  count: Long
)

// error types
case class ErrorInfo(error: String)

object GithubStatsRESTAPI {
  
  // endpoints as values
  // curl -X GET -d '{"username": "ed"}' http://localhost:8080/api/1.0/summary/user
  val baseEndpoint =
    endpoint
      .in("api" / "1.0" / "summary")
      .errorOut(statusCode.and(jsonBody[ErrorInfo]))

  val userSummary = 
    baseEndpoint
      .get
      .in("user")
      .in(jsonBody[Username])
      .out(jsonBody[UserSummary])

  val repoSummary = 
    baseEndpoint
      .get
      .in("repo")
      .in(jsonBody[Reponame])
      .out(jsonBody[RepoSummary])

  val allAppRoutes = List(
    userSummary,
    repoSummary
  )
  
}
