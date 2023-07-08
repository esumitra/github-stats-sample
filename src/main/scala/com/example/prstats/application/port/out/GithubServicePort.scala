package com.example.prstats.application.port.out

import com.example.prstats.domain.{PRItem}

trait GithubServicePort {
  def searchByUser(username: String): List[PRItem]
  def searchByRepo(reponame: String): List[PRItem]
}
