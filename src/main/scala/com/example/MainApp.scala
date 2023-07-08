/**
  * main entry point for application
  */

package com.example

import com.example.config.{ConfigUtils, CookieSettings, GithubStatsConfig}
import pureconfig.generic.auto._
import com.typesafe.scalalogging.{LazyLogging}
import com.example.prstats.adapter.in.GithubStatsAkkaHttpAdapter
import com.example.prstats.application.PRStatsService
import com.example.prstats.adapter.out.GithubServiceTestAdapter

object MainApp extends LazyLogging {

  val COOKIE_CONFIG_PATH="com.example.cookie"

  def hello(name: String): String = s"Hello ${name}"

  def main(args: Array[String]): Unit = {
    val cookie = ConfigUtils.loadAppConfig[CookieSettings](COOKIE_CONFIG_PATH)
    logger.info(s"running application version with ttl: ${cookie.ttl}")
    val ghConf: GithubStatsConfig = GithubStatsConfig.conf

    logger.info(s"stats conf:\n ${ghConf}")
    val message = args.length match {
      case 0 => hello("Anonymous")
      case _ => hello(args(0))
    }
    println(message)
    startAPIServer(ghConf)
  }

  def startAPIServer(ghConf: GithubStatsConfig): Unit = {
    implicit val ghService: GithubServiceTestAdapter = GithubServiceTestAdapter()
    implicit val useCase = new PRStatsService
    val server = new GithubStatsAkkaHttpAdapter(ghConf.serverPort)
    server.startServer
  }
}
