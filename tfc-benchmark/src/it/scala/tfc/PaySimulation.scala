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
      _.origin :~ "bank",
      _.destination :~ "bank",
      _.amount :~ "0"
    ))
    .extract(_.validated.some)(_ is true)

  val scn: ScenarioBuilder = scenario("Basic Pay Simulation") // A scenario is a chain of requests and pauses
    .exec(request)


  setUp(scn.inject(
    //atOnceUsers(10)

    rampUsersPerSec(30) to 300 during (10),
    constantUsersPerSec(300) during (50),
    rampUsersPerSec(50) to 1000 during (20),
  )
    .protocols(grpcConf))

}