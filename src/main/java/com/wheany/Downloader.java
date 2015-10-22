package com.wheany;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * User: Jan-Erik Finnberg
 * Date: 5.10.2015
 * Time: 13:52
 */
public class Downloader {
    private final URL url;

    public Downloader(String url) throws MalformedURLException {
        this.url = new URL(url);
    }
    public Downloader(URL url) {
        this.url = url;
    }

    public Path download() throws IOException {
        URLConnection conn = url.openConnection();
        final Path workDir = Paths.get("rssifier-work");
        Files.createDirectories(workDir);

        Path workFile = Files.createTempFile(workDir, "rssfier-tmp", ".html");
        workFile.toFile().deleteOnExit();

        try (final InputStream in = conn.getInputStream()) {
            Files.copy(in, workFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return workFile;
    }
}
