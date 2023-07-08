package com.example.prstats.application.port.in

trait PRSummaryUseCase {
  def userSummary(userName: String): Int
  def repoSummary(repoName: String): Int
}
