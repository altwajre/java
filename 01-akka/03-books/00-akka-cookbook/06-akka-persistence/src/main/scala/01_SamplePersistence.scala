import akka.actor.{ActorRef, ActorSystem, Props}
import akka.persistence.{PersistentActor, SnapshotOffer}

/*
# Preparing an actor for persistence

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/615519b7-93d1-4de9-a748-8a79b0ff413e.xhtml

Before we take a look at Akka persistence, let's review the terminology and architectures used in it:

- PersistenceActor: A stateful actor that persists events to the journal. When a persistent actor is started or restarted,
journaled messages are replayed to that actor so that it can recover the internal state from these messages.

- AsyncWriteJournal: A journal keeps an ordered collection of events that can be sent to a persistent actor.
An application can control which messages are journaled and which are received by persist actor without being journaled.
The data store behind the journal is configurable and needs to be decided depending on the needs.
Akka persistence comes with a LevelDB journal plugin (which uses the local filesystem and is not replicated)

- AtLeastOnceDelivery: A messaging delivery mechanism that ensures at-least-once delivery semantics to destinations.

- Snapshot store: A snapshot store persists snapshots of a persistent actor's. Snapshots are used to speed up recovery time from the journal.
The data store behind the snapshot store is configurable (the same as the journal) and uses the local filesystem as the default.


 */
sealed trait UserAction
case object Add extends UserAction
case object Remove extends UserAction
case class UserUpdate(userId: String, action: UserAction)

sealed trait Event
case class AddUserEvent(userId: String) extends Event
case class RemoveUserEvent(userId: String) extends Event

case class ActiveUsers(users: Set[String] = Set.empty[String])
{
  def update(evt: Event) = evt match {
    case AddUserEvent(userId) => copy(users + userId)
    case RemoveUserEvent(userId) => copy(users.filterNot(_ == userId))
  }

  override def toString: String = s"$users"
}

class SamplePersistenceActor extends PersistentActor {
  override def persistenceId: String = "unique-id-1"
  var state = ActiveUsers()
  def updateState(event: Event) = state = state.update(event)

  override def receiveRecover: Receive = {
    case evt: Event => updateState(evt)
    case SnapshotOffer(_, snapshot: ActiveUsers) => state = snapshot
  }

  override def receiveCommand: Receive = {
    case UserUpdate(userId, Add) => {
      persist(AddUserEvent(userId))(updateState)
    }
    case UserUpdate(userId, Remove) => {
      persist(RemoveUserEvent(userId))(updateState)
    }
    case "snap" => saveSnapshot(state)
    case "print" => println(state)
  }
}

object SamplePersistenceApp extends App {
  private val system = ActorSystem("SamplePersistence")
  private val actor1: ActorRef = system.actorOf(Props[SamplePersistenceActor])
  actor1 ! UserUpdate("foo", Add)
  actor1 ! UserUpdate("bar", Add)
  actor1 ! "snap"
  actor1 ! "print"
  actor1 ! UserUpdate("bar", Remove)
  actor1 ! "print"
  Thread.sleep(2000)
  system.terminate()
}
/*
[WARN] [SECURITY][06/12/2018 09:11:57.197] [SamplePersistence-akka.persistence.dispatchers.default-plugin-dispatcher-5] [akka.serialization.Serialization(akka://SamplePersistence)] Using the default Java serializer for class [AddUserEvent] which is not recommended because of performance implications. Use another serializer or disable this warning using the setting 'akka.actor.warn-about-java-serializer-usage'
Set(foo, bar)
[WARN] [SECURITY][06/12/2018 09:11:57.230] [SamplePersistence-akka.persistence.dispatchers.default-plugin-dispatcher-5] [akka.serialization.Serialization(akka://SamplePersistence)] Using the default Java serializer for class [RemoveUserEvent] which is not recommended because of performance implications. Use another serializer or disable this warning using the setting 'akka.actor.warn-about-java-serializer-usage'
[WARN] [SECURITY][06/12/2018 09:11:57.230] [SamplePersistence-akka.persistence.dispatchers.default-stream-dispatcher-7] [akka.serialization.Serialization(akka://SamplePersistence)] Using the default Java serializer for class [ActiveUsers] which is not recommended because of performance implications. Use another serializer or disable this warning using the setting 'akka.actor.warn-about-java-serializer-usage'
Set(foo)
 */
