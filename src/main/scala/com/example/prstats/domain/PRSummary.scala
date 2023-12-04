package com.example.prstats.domain

import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined._
import eu.timepit.refined.boolean._
import eu.timepit.refined.string._
import eu.timepit.refined.boolean._
import eu.timepit.refined.collection._

object PRDomainModel {

  type PRCount = Long Refined Positive
  type UserName = String Refined MinSize[3]
  type RepositoryName = String Refined MatchesRegex["""^[\p{Alnum}]{4,}"""] 

  final case class PRItem(
    id: String,
    title: String
  )

  final case class PRUserSummary(
    username: UserName,
    count: PRCount
  )

  final case class PRRepoSummary(
    reponame: RepositoryName,
    count: PRCount
  )
}
