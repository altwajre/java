package com.company.app.resources;

import com.company.app.model.Topic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/long-run")
@Produces(MediaType.APPLICATION_JSON)
public class LongRunResource {
    static int count = 0;
    static Map<Integer, Topic> topics = new HashMap<Integer, Topic>();

    @GET
    public Response getTopics() {
        count++;

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Topic topic = new Topic();
        topic.setId(count);
        topic.setName("topic-" + count);

        System.out.println(topic);

        topics.put(topic.getId(), topic);
        return Response.ok(topics).build();
    }
}
