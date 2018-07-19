import actors.messages.Schedule
import akka.actor.{ActorSystem, Props}
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/*
Application specifications

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/c7a1f864-3bf8-4680-81bb-eb210c166cb8.xhtml

Create a scheduler application that can run console commands or SQL queries against a database.
The user should be able to schedule any command or query using configuration files,
and they should be able to choose a granularity—hourly or daily, at a specific time.

Support following operations:
- Read an application configuration
- Read scheduler configuration files
- Schedule tasks
- Exchange messages
- Access a database
- Execute console commands

Akka Actors
- Master
when a message is received, master actor schedules a list of jobs by creating work items and sending them to the router.
The router is a round-robin pool of workers, so every task will go to a different worker.
 */

object Scheduler extends LazyLogging {
  import registry.ComponentRegistry._
  def main(args: Array[String]): Unit = {
    logger.info("Running migrations before doing anything else.")
    migrationService.runMigrations()
    logger.info("Migrations done!")

    val system = ActorSystem("scheduler")

    val master = system.actorOf(
      Props(actorFactory.createMasterActor()),
      "scheduler-master"
    )

    sys.addShutdownHook({
      logger.info("Awaiting actor system termination.")
      // not great...
      Await.result(system.terminate(), Duration.Inf)
      logger.info("Actor system terminated. Bye!")
    })

    master ! Schedule(jobConfigReaderService.readJobConfigs())
    logger.info("Started! Use CTRL+C to exit.")
  }
}
