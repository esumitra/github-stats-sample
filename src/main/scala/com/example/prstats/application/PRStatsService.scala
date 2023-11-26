package com.example.prstats.application

import com.example.prstats.application.port.out.GithubSearchPort
import com.example.prstats.application.port.in.PRSummaryUseCase

class PRStatsService(implicit ghService: GithubSearchPort) extends PRSummaryUseCase {

  override def userSummary(userName: String): Int = {
    ghService.searchByUser(userName).size
  }

  override def repoSummary(repoName: String): Int = {
    ghService.searchByRepo(repoName).size
  }

}
  
