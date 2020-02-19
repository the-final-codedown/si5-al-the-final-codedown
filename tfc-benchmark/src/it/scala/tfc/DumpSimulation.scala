package tfc

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.HttpRequest

class DumpSimulation extends Simulation {

  val url = "http://" + sys.env("DUMP_URL")

  val scn: ScenarioBuilder = scenario("Dump Scenario")
    .exec(http("dump micro")
      .get(url + "/dump")
      .check(status is 200)
    )
  setUp(
    scn.inject(
      rampUsersPerSec(5) to 30 during (60),
      constantUsersPerSec(30) during (120),
    ))
}