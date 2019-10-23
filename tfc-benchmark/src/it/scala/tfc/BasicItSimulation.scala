package tfc

import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.grpc.ManagedChannelBuilder
import org.slf4j.LoggerFactory
import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.pb._
import com.github.phisgr.gatling.util._

class BasicItSimulation extends Simulation {
  //val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
  // Log all HTTP requests
  //context.getLogger("io.gatling.http").setLevel(Level.valueOf("INFO"))
  //val grpcConf = grpc(ManagedChannelBuilder.forAddress("photoslibrary.googleapis.com", 443))
  /*val request = grpc("request_1")
    .rpc(HealthGrpc.METHOD_CHECK)
    .payload(HealthCheckRequest.defaultInstance)
    .extract(_.status.some)(_ is SERVING)*/

//   val grpcConf2 = grpc(ManagedChannelBuilder.forAddress("localhost/Cap/CapUpdaterService/", 8080))

  /* val request2 = grpc("request_2")
     .rpc(CapUpdaterServiceGrpc.METHOD_DOWNSCALE_CAP)
     .payload(CapDownscale.defaultInstance)
     .extract(_.accepted.some)(_ is true)

    val scn2 = scenario("Scenario Name") // A scenario is a chain of requests and pauses
      .exec(request2)
      //.pause(7.seconds)

    //setUp(scn.inject(atOnceUsers(1)).protocols(grpcConf))
    setUp(scn2.inject(atOnceUsers(1)).protocols(grpcConf2))
*/
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080/cap/CapUpdaterService")

    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,**;q=0.8")
    //.doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn: ScenarioBuilder = scenario("Model")
    .repeat(1) {
      exec(http("post url")
        .post("/DownscaleCap")

        //.("AccountID", "123")
        //.queryParam("delta", "10")
        .check(status is 200)
      )
    }

  /*.exec(
    http("get model")
      .get("/models/${id}")
      .check(status is 200)
      .check(jsonPath("$.name").is("gatling"))
  )
  .exec(
    http("patch model")
      .patch("/models/${id}")
      .queryParam("name", "gatling2")
      .check(jsonPath("$.name").is("gatling2"))
  )
  .exec(http("delete models")
    .delete("/models/${id}")
    .check(status is 200)
  )
  .exec(http("get model")
    .get("/models/${id}")
    .check(status is 404)
  )*/

  setUp(
    scn.inject(//nothingFor(1), // 1
      //atOnceUsers(10), // 2
      rampConcurrentUsers(1) to 100 during(60)
      /*
      rampUsers(20) during(5), // 3
      constantUsersPerSec(50) during(15), // 4
      constantUsersPerSec(50) during(15) randomized, // 5
      rampUsersPerSec(10) to 20 during(100), // 6
      rampUsersPerSec(10) to 20 during(10 minutes) randomized, // 7
      splitUsers(1000) into(rampUsers(10) over(10 seconds)) separatedBy(10 seconds), // 8
      splitUsers(1000) into(rampUsers(10) over(10 seconds)) separatedBy atOnceUsers(30), // 9
      heavisideUsers(1000) over(20 seconds) 10)
      */
    )).protocols(httpProtocol)

}