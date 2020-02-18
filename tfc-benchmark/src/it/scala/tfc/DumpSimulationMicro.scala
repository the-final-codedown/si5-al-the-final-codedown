
class BasicSimulation extends Simulation {


  val scn: ScenarioBuilder = scenario("DumpScenario")
    .exec(http("dump")
      .get("http://localhost:8085/dump")
      .check(status is 200)
    )
  setUp(
    scn.inject(
      rampUsersPerSec(5) to 50 during (30),
      constantUsersPerSec(50) during (30)
    ))
}