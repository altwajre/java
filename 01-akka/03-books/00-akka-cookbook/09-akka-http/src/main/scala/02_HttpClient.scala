import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.duration._

import scala.util.Success

/*
Consuming Akka HTTP services using a client-side API

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/c45a1203-52f7-4a2c-a6bf-ec76df5f0dba.xhtml

# Manual Test
curl https://api.github.com/repos/akka/akka-http
 */
/*
Recommended

The most straightforward level.
Call Http().singleRequest, Akka HTTP triggers the request in the execution context and gives a Future[HttpResponse]
back to consume the result when it becomes available.
 */
object RequestLevelClientAPIApplication extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val akkaToolkitRequest = HttpRequest(uri = "https://api.github.com/repos/akka/akka-http")
  val responseFuture = Http().singleRequest(akkaToolkitRequest)

  responseFuture.andThen {
    case Success(response) =>
      response.entity.toStrict(5 seconds)
        .map(_.data.decodeString("UTF-8"))
        .andThen {
          case Success(json) =>
            val pattern = """.*"open_issues":(.*?),.*""".r
            pattern.findAllIn(json).matchData foreach { m =>
              println(s"There are ${m.group(1)} open issues in Akka Http.")
              materializer.shutdown()
              system.terminate()
            }
          case _ =>
        }
    case _ => println(s"request failed")
  }
}
/*
There are 480 open issues in Akka Http.
 */

object ConnectionLevelClientAPIApplication extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val connectionFlow = Http().outgoingConnectionHttps("api.github.com")
  val akkaToolkitRequest = HttpRequest(uri = "/repos/akka/akka-http")

  val responseFuture = Source.single(akkaToolkitRequest)
    .via(connectionFlow).runWith(Sink.head)

  responseFuture.andThen {
    case Success(response) =>
      response.entity.toStrict(5 seconds).map(
        _.data.decodeString("UTF-8")).andThen {
        case Success(json) =>
          println(json)
          val pattern = """.*"open_issues":(.*?),.*""".r
          pattern.findAllIn(json).matchData foreach { m =>
            println(s"There are ${m.group(1)} open issues in Akka Http.")
            materializer.shutdown()
            system.terminate()
          }
        case _ =>
      }
    case _ => println("request failed")
  }
}
/*
{"id":66077955,"node_id":"MDEwOlJlcG9zaXRvcnk2NjA3Nzk1NQ==","name":"akka-http","full_name":"akka/akka-http","owner":{"login":"akka","id":1496237,"node_id":"MDEyOk9yZ2FuaXphdGlvbjE0OTYyMzc=","avatar_url":"https://avatars2.githubusercontent.com/u/1496237?v=4","gravatar_id":"","url":"https://api.github.com/users/akka","html_url":"https://github.com/akka","followers_url":"https://api.github.com/users/akka/followers","following_url":"https://api.github.com/users/akka/following{/other_user}","gists_url":"https://api.github.com/users/akka/gists{/gist_id}","starred_url":"https://api.github.com/users/akka/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/akka/subscriptions","organizations_url":"https://api.github.com/users/akka/orgs","repos_url":"https://api.github.com/users/akka/repos","events_url":"https://api.github.com/users/akka/events{/privacy}","received_events_url":"https://api.github.com/users/akka/received_events","type":"Organization","site_admin":false},"private":false,"html_url":"https://github.com/akka/akka-http","description":"The Streaming-first HTTP server/module of Akka","fork":false,"url":"https://api.github.com/repos/akka/akka-http","forks_url":"https://api.github.com/repos/akka/akka-http/forks","keys_url":"https://api.github.com/repos/akka/akka-http/keys{/key_id}","collaborators_url":"https://api.github.com/repos/akka/akka-http/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/akka/akka-http/teams","hooks_url":"https://api.github.com/repos/akka/akka-http/hooks","issue_events_url":"https://api.github.com/repos/akka/akka-http/issues/events{/number}","events_url":"https://api.github.com/repos/akka/akka-http/events","assignees_url":"https://api.github.com/repos/akka/akka-http/assignees{/user}","branches_url":"https://api.github.com/repos/akka/akka-http/branches{/branch}","tags_url":"https://api.github.com/repos/akka/akka-http/tags","blobs_url":"https://api.github.com/repos/akka/akka-http/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/akka/akka-http/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/akka/akka-http/git/refs{/sha}","trees_url":"https://api.github.com/repos/akka/akka-http/git/trees{/sha}","statuses_url":"https://api.github.com/repos/akka/akka-http/statuses/{sha}","languages_url":"https://api.github.com/repos/akka/akka-http/languages","stargazers_url":"https://api.github.com/repos/akka/akka-http/stargazers","contributors_url":"https://api.github.com/repos/akka/akka-http/contributors","subscribers_url":"https://api.github.com/repos/akka/akka-http/subscribers","subscription_url":"https://api.github.com/repos/akka/akka-http/subscription","commits_url":"https://api.github.com/repos/akka/akka-http/commits{/sha}","git_commits_url":"https://api.github.com/repos/akka/akka-http/git/commits{/sha}","comments_url":"https://api.github.com/repos/akka/akka-http/comments{/number}","issue_comment_url":"https://api.github.com/repos/akka/akka-http/issues/comments{/number}","contents_url":"https://api.github.com/repos/akka/akka-http/contents/{+path}","compare_url":"https://api.github.com/repos/akka/akka-http/compare/{base}...{head}","merges_url":"https://api.github.com/repos/akka/akka-http/merges","archive_url":"https://api.github.com/repos/akka/akka-http/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/akka/akka-http/downloads","issues_url":"https://api.github.com/repos/akka/akka-http/issues{/number}","pulls_url":"https://api.github.com/repos/akka/akka-http/pulls{/number}","milestones_url":"https://api.github.com/repos/akka/akka-http/milestones{/number}","notifications_url":"https://api.github.com/repos/akka/akka-http/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/akka/akka-http/labels{/name}","releases_url":"https://api.github.com/repos/akka/akka-http/releases{/id}","deployments_url":"https://api.github.com/repos/akka/akka-http/deployments","created_at":"2016-08-19T11:33:36Z","updated_at":"2018-08-24T04:21:04Z","pushed_at":"2018-08-24T04:20:57Z","git_url":"git://github.com/akka/akka-http.git","ssh_url":"git@github.com:akka/akka-http.git","clone_url":"https://github.com/akka/akka-http.git","svn_url":"https://github.com/akka/akka-http","homepage":"http://akka.io","size":10367,"stargazers_count":751,"watchers_count":751,"language":"Scala","has_issues":true,"has_projects":true,"has_downloads":true,"has_wiki":false,"has_pages":true,"forks_count":358,"mirror_url":null,"archived":false,"open_issues_count":480,"license":{"key":"other","name":"Other","spdx_id":null,"url":null,"node_id":"MDc6TGljZW5zZTA="},"forks":358,"open_issues":480,"watchers":751,"default_branch":"master","organization":{"login":"akka","id":1496237,"node_id":"MDEyOk9yZ2FuaXphdGlvbjE0OTYyMzc=","avatar_url":"https://avatars2.githubusercontent.com/u/1496237?v=4","gravatar_id":"","url":"https://api.github.com/users/akka","html_url":"https://github.com/akka","followers_url":"https://api.github.com/users/akka/followers","following_url":"https://api.github.com/users/akka/following{/other_user}","gists_url":"https://api.github.com/users/akka/gists{/gist_id}","starred_url":"https://api.github.com/users/akka/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/akka/subscriptions","organizations_url":"https://api.github.com/users/akka/orgs","repos_url":"https://api.github.com/users/akka/repos","events_url":"https://api.github.com/users/akka/events{/privacy}","received_events_url":"https://api.github.com/users/akka/received_events","type":"Organization","site_admin":false},"network_count":358,"subscribers_count":88}
There are 480 open issues in Akka Http.
 */

object HostLevelClientAPIApplication extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val poolClientFlow = Http().cachedHostConnectionPoolHttps[String]("api.github.com")
  val akkaToolkitRequest = HttpRequest(uri = "/repos/akka/akka-http") -> """.*"open_issues":(.*?),.*"""
  val responseFuture = Source.single(akkaToolkitRequest)
    .via(poolClientFlow).runWith(Sink.head)

  responseFuture.andThen {
    case Success(result) =>
      val (tryResponse, regex) = result
      tryResponse match {
        case Success(response) =>
          response.entity.toStrict(5 seconds)
            .map(_.data.decodeString("UTF-8"))
            .andThen {
              case Success(json) =>
                val pattern = regex.r
                pattern.findAllIn(json).matchData foreach { m =>
                  println(s"There are ${m.group(1)} open issues in Akka Http.")
                  materializer.shutdown()
                  system.terminate()
                }
              case _ =>
            }
        case _ => println("request failed")
      }
    case _ => println("request failed")
  }
}

/*
There are 480 open issues in Akka Http.
 */

