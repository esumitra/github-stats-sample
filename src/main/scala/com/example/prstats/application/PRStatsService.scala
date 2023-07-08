package com.example.prstats.application

import com.example.prstats.application.port.out.GithubServicePort
import com.example.prstats.application.port.in.PRSummaryUseCase

class PRStatsService(implicit ghService: GithubServicePort) extends PRSummaryUseCase {

  override def userSummary(userName: String): Int = {
    ghService.searchByUser(userName).size
  }

  override def repoSummary(repoName: String): Int = {
    ghService.searchByUser(repoName).size
  }

}
  
