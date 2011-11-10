package com.yammer.dropwizard.example;

import com.google.common.collect.ImmutableList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("ALL")
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final AtomicInteger id;
    private final String saying;

    public HelloWorldResource(String saying) {
        this.id = new AtomicInteger();
        this.saying = saying;
    }

    @GET
    public ImmutableList<Saying> sayHello() {
        return ImmutableList.of(new Saying(id.incrementAndGet(), saying));
    }

    @POST
    public Response intentionalError() {
        return Response.status(Status.BAD_REQUEST).build();
    }

    @PUT
    public void unintentionalError() {
        final String blah = null;
        blah.charAt(0);
    }
}
