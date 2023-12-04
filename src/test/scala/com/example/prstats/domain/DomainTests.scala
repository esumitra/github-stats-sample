package com.example.prstats.domain
import com.example.testutils.StandardTest
import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.boolean._
import eu.timepit.refined.char._
import eu.timepit.refined.collection._
import eu.timepit.refined.api.RefType
import PRDomainModel._
class DomainTests extends StandardTest {

  "A Set" when {
    "empty" should {
      "have size 0" in {
        assert(Set.empty.size == 0)
      }
    }
  }

  "The PR Domain Model" when {
    "PRSummary" should {
      "allow a positive count" in {
        PRUserSummary("user1", 5L)
      }
      // "reject a negative count" in {
      //   PRUserSummary("user1", -5L)
      // }
      "allow a valid repository name" in {
        PRRepoSummary("repo1", 5L)
      }
      // "reject an invalid repository name" in {
      //   PRRepoSummary("re1", 5L)
      // }
    }
  }


  

}
