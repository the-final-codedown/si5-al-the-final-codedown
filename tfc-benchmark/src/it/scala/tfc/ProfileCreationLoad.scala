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

class ProfileCreationLoad extends Simulation {
  private val transfer_validator_host: String = System.getProperty("TRANSFER_VALIDATOR_HOST", "localhost")
  private val transfer_validator_port = Integer.getInteger("TRANSFER_VALIDATOR_PORT", 50052);
  private val profile_host: String = sys.env("PROFILE_HOST");
  private val profile_port = Integer.parseInt(sys.env("PROFILE_PORT"));
  private val account_host: String = sys.env("ACCOUNT_HOST")
  private val account_port = Integer.parseInt(sys.env("ACCOUNT_PORT"));
  val grpcConf = grpc(ManagedChannelBuilder.forAddress(transfer_validator_host, transfer_validator_port).usePlaintext())
  val accountProvisioning: GrpcCallActionBuilder[Transfer, TransferValidation] = grpc("pay")
    .rpc(TransferValidatorServiceGrpc.METHOD_PAY)
    .payload(Transfer.defaultInstance.updateExpr(
      _.origin :~ "bank",
      _.destination :~ "${accountId}",
      _.amount :~ "100"
    ))
    .extract(_.validated.some)(_ is true)
  val feeder = Iterator.continually(Map("email" -> (Random.alphanumeric.take(20).mkString + "@foo.com")))
  val profileCreation_url = s"http://${profile_host}:${profile_port}/profiles"
  print(profileCreation_url)
  val accountCreation_url = s"http://${account_host}:${account_port}/accounts/" + "${email}/accounts"
  val profile_creation_request = http("Profile Creation")
    .post(profileCreation_url)
    .header(HttpHeaderNames.ContentType, HttpHeaderValues.ApplicationJson)
    .body(StringBody("""{"email":"${email}"}"""))

  val account_creation_request = http("Account Creation").post(accountCreation_url)
    .header(HttpHeaderNames.ContentType, HttpHeaderValues.ApplicationJson)
    .body(StringBody("""{"accountType":"SAVINGS","money":100} """))
    .check(jsonPath("$..accountId").find.saveAs("accountId"))

  val creation_scenario: ScenarioBuilder = scenario("Account Creation").feed(feeder)
    .exec(profile_creation_request)
    .exec(account_creation_request)
    .exec(accountProvisioning)

  val constant = 400;
  val low = 120;
//  setUp(creation_scenario.inject(
//    constantUsersPerSec(10) during (1),
//  ).protocols(grpcConf))
    setUp(
      creation_scenario.inject(
        rampUsersPerSec(0) to constant during(30),
        constantUsersPerSec(constant) during(120),
        constantUsersPerSec(low) during(60),
        constantUsersPerSec(constant) during(120),
      ).protocols(grpcConf)

    )
}
