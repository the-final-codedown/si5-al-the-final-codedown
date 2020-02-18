/*
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
class BasicSimulation extends Simulation {


  val scn: ScenarioBuilder = scenario("DumpScenario")
    .exec(http("dump")
      .get("http://localhost:8081/dump")
      .check(status is 200)
    )
  setUp(
    scn.inject(
      rampUsersPerSec(5) to 50 during (30),
      constantUsersPerSec(50) during (30)
    ))
}*/