package tfc

import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.grpc.action.GrpcCallActionBuilder
import com.github.phisgr.gatling.pb._
import com.github.phisgr.gatling.util._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.grpc.ManagedChannelBuilder
import tfc.transfer.validator.tfc_transfer_validator.{Transfer, TransferValidation, TransferValidatorServiceGrpc}

class PaySimulation extends Simulation {

  val grpcConf = grpc(ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext())

  val request: GrpcCallActionBuilder[Transfer, TransferValidation] = grpc("pay")
    .rpc(TransferValidatorServiceGrpc.METHOD_PAY)
    .payload(Transfer.defaultInstance.updateExpr(
      _.origin :~ "0",
      _.destination :~ "1",
      _.amount :~ "-10"
    ))
    .extract(_.validated.some)(_ is true)

  val scn: ScenarioBuilder = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(request)


  setUp(scn.inject(
    atOnceUsers(1)

    //rampUsersPerSec(5) to 30 during (60),
    //constantUsersPerSec(30) during (60)
  )
    .protocols(grpcConf))

}