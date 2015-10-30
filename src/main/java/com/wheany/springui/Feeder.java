package com.wheany.springui;

import com.wheany.Util;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class Feeder {
    @RequestMapping(value = "rssifier/feed/{id}", method = RequestMethod.GET)
    public void getFile(@PathVariable("id") String id, HttpServletResponse response) {
        response.setContentType("application/rss+xml");
        try {
            Path feedFile = Util.getPathFromName(id).resolve("feed.xml");
            if (!feedFile.toFile().exists()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            Files.copy(feedFile, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream", ex);
        }
    }
}
