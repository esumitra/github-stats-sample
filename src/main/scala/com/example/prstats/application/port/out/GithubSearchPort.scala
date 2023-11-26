package com.example.prstats.application.port.out

import com.example.prstats.domain.{PRItem}

trait GithubSearchPort {
  def searchByUser(username: String): List[PRItem]
  def searchByRepo(reponame: String): List[PRItem]
}
