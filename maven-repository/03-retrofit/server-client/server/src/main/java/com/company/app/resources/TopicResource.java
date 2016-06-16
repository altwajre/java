package com.company.app.resources;

import com.company.app.Topic;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/topics")
@Produces(MediaType.APPLICATION_JSON)
public class TopicResource {

    static Map<Integer, Topic> topics = new HashMap<Integer, Topic>();

    @GET
    public Response getTopics() {
        Topic topic = new Topic();
        topic.setId(1);
        topic.setName("topic-1");

        System.out.println(topic);

        topics.put(topic.getId(), topic);
        return Response.ok(topics).build();
    }

    @POST()
    public Response createTopic(Topic topic){
        topics.put(topic.getId(), topic);
        return Response.status(Response.Status.CREATED)
                .entity(topic)
                .build();
    }
}
