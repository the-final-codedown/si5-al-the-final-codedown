package tfc
import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.grpc.action.GrpcCallActionBuilder
import com.github.phisgr.gatling.pb._
import com.github.phisgr.gatling.util._
import com.github.phisgr.gatling.grpc.Predef.grpc
import com.github.phisgr.gatling.grpc.action.GrpcCallActionBuilder
import io.gatling.core.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.http.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.HttpRequest
import io.grpc.ManagedChannelBuilder
import tfc.transfer.validator.tfc_transfer_validator.{Transfer, TransferValidation, TransferValidatorServiceGrpc}

import scala.util.Random

class ProfileCreationLoad extends Simulation{
  val grpcConf = grpc(ManagedChannelBuilder.forAddress( System.getProperty("TRANSFER_VALIDATOR_HOST","localhost"), 50052).usePlaintext())
  val accountProvisioning: GrpcCallActionBuilder[Transfer, TransferValidation] = grpc("pay")
    .rpc(TransferValidatorServiceGrpc.METHOD_PAY)
    .payload(Transfer.defaultInstance.updateExpr(
      _.origin :~ "bank",
      _.destination :~ "${accountId}",
      _.amount :~ "100"
    ))
    .extract(_.validated.some)(_ is true)
  val feeder = Iterator.continually(Map("email" -> (Random.alphanumeric.take(20).mkString + "@foo.com")))
  val profileCreation_url = "/profiles"
  val accountCreation_url = "/profiles/${email}/accounts"
  val profile_creation_request = http("Profile Creation").post(profileCreation_url).body(StringBody("${email}"))
  val account_creation_request = http("Account Creation").post(accountCreation_url)
    .header(HttpHeaderNames.ContentType,HttpHeaderValues.ApplicationJson)
    .body(StringBody("""{"accountType":"CHECK","money":100} """)).check(jsonPath("$..accountId").find.saveAs("accountId"))
  val creation_scenario : ScenarioBuilder = scenario("Account Creation").feed(feeder).exec(profile_creation_request).exec(account_creation_request).exec(accountProvisioning)

  val protocol = http.baseUrl("http://localhost:8081")

  setUp(
    creation_scenario.inject(
      atOnceUsers(30),
      rampUsersPerSec(30) to 300 during(30),
      constantUsersPerSec(300) during(30),
      rampUsersPerSec(300) to 1000 during(15),
      constantUsersPerSec(1000) during(15),
      constantUsersPerSec(100) during(30),
      rampUsersPerSec(100) to 1000 during 15,
    ).protocols(protocol,grpcConf)

  )
}
