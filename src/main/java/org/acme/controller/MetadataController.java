package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.service.MetadataService;

import java.util.List;

@Path("/api/metadata")
public class MetadataController {

    @Inject
    MetadataService metadataService;

    @GET
    @Path("/tables")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTables(@QueryParam("schema") String schema) {
        try {
            List<String> tables = metadataService.getTables(schema);
            return Response.ok(tables).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage())
                    .build();
        }
    }


}
