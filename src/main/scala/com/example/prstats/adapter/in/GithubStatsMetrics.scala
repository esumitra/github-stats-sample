package com.example.prstats.adapter.in

import sttp.tapir.server.metrics.prometheus.PrometheusMetrics
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import sttp.tapir.server.akkahttp.{AkkaHttpServerInterpreter, AkkaHttpServerOptions}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object GithubStatsMetrics {
  // an instance with default metrics; use PrometheusMetrics[Future]() for an empty one
  val prometheusMetrics = PrometheusMetrics.default[Future]()

  // enable metrics collection
  val serverOptions: AkkaHttpServerOptions =
    AkkaHttpServerOptions
      .customiseInterceptors
      .metricsInterceptor(prometheusMetrics.metricsInterceptor())
      .options
}
