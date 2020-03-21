package dev.ruivieira.kafkaping;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Routes {

    @Inject
    SimpleProducer simpleProducer;

    @POST
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{topic}")
    public Response sendMessage(@PathParam String topic, String payload) {
        simpleProducer.sendMessage(topic, payload);
        return Response.ok().build();
    }
}