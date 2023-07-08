package com.example.prstats.domain

final case class PRItem(
  id: String,
  title: String
)

final case class PRUserSummary(
  username: String,
  count: Long
)

final case class PRRepoSummary(
  reponame: String,
  count: Long
)
