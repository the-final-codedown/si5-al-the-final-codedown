package tfc

import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.grpc.action.GrpcCallActionBuilder
import com.github.phisgr.gatling.pb._
import com.github.phisgr.gatling.util._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.grpc.ManagedChannelBuilder
import tfc.cap.updater.tfc_cap_updater.{CapDownscale, CapUpdaterServiceGrpc, DownscaleResponse}

class BasicItSimulation extends Simulation {

  /*val grpcConf = grpc(ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext())

  val request: GrpcCallActionBuilder[CapDownscale, DownscaleResponse] = grpc("downscale")
    .rpc(CapUpdaterServiceGrpc.METHOD_DOWNSCALE_CAP)
    .payload(CapDownscale.defaultInstance.updateExpr(
      _.accountID :~ "100",
      _.value :~ "10"
    ))
    .extract(_.accepted.some)(_ is true)

  val scn: ScenarioBuilder = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .repeat(25) {
      exec(request)
    }

  setUp(scn.inject(
    //atOnceUsers(0)) // 2

    rampUsersPerSec(5) to 30 during (60),
    constantUsersPerSec(30) during (60)
  )

    .protocols(grpcConf))*/
}