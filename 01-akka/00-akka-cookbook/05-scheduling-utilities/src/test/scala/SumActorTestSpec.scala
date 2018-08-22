import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class TestSpec() extends TestKit(ActorSystem("TestSpec")) with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {
  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "Sum actor" must {
    "send back sum of two integers" in {
      val sumActor = system.actorOf(Props[SumActor])
      sumActor ! (2, 3)
      expectMsg(5)
    }
  }
}
