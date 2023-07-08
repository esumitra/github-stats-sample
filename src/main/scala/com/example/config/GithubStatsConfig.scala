package com.example.config

import pureconfig.generic.auto._

final case class GithubStatsConfig(
  ghApiKey: String,
  serverPort: Int
)

object GithubStatsConfig {
  lazy val conf = ConfigUtils.loadAppConfig[GithubStatsConfig]("com.example.github-stats")
}
