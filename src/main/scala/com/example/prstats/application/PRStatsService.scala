package com.example.prstats.application

import com.example.prstats.application.port.out.GithubSearchPort
import com.example.prstats.application.port.in.PRSummaryUseCase
import com.example.prstats.domain.PRDomainModel._
import eu.timepit.refined.api.RefType

class PRStatsService(implicit ghService: GithubSearchPort) extends PRSummaryUseCase {

  def validateUser(user: String): Boolean  =
    RefType.applyRef[UserName](user).isRight

  def validateRepo(name: String): Boolean =
    !name.startsWith("x") &&
    RefType.applyRef[RepositoryName](name).isRight

  override def userSummary(userName: String): Int =
    // apply business rules
    if (!validateUser(userName)) 0
    else ghService.searchByUser(userName).size

  override def repoSummary(repoName: String): Int = {
    // apply business rules
    if (!validateRepo(repoName)) 0
    else ghService.searchByRepo(repoName).size
  }

}
  
