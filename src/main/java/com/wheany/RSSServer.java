package com.wheany;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("feed")
public class RSSServer {
    @GET
    @Path("{id}")
    @Produces("application/rss+xml")
    public Response downloadRSSFeed(@PathParam("id") String id) {
        java.nio.file.Path dirPath = Util.getPathFromName(id);

        if (!dirPath.toFile().exists()) {
            throw new NotFoundException("path '" + id + "' not found");
        }
        java.nio.file.Path feed = dirPath.resolve("feed.xml");

        if (!feed.toFile().exists()) {
            throw new NotFoundException("feed '" + id + "' not found");
        }

        Response.ResponseBuilder response = Response.ok(feed.toFile());
        response.type("application/rss+xml");

        return response.build();
    }
}
