package com.example.prstats.adapter.out

import com.example.prstats.application.port.out.GithubServicePort
import com.example.prstats.domain.{PRItem}

final case class GithubServiceTestAdapter() extends GithubServicePort {

  override def searchByUser(username: String): List[PRItem] =
    GithubServiceTestAdapter.sampleList1
  override def searchByRepo(reponame: String): List[PRItem] = 
    GithubServiceTestAdapter.sampleList1 ++ GithubServiceTestAdapter.sampleList1
}

object GithubServiceTestAdapter {
  val sampleList1 = 
     List(
      PRItem("120", "Initial checkin"),
      PRItem("121", "update readme"),
    )

}
