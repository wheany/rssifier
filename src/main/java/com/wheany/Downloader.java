package com.wheany;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Downloader {
    private final Path baseDir;

    public Downloader(Path baseDir) {
        this.baseDir = baseDir;
    }

    public Path download(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        final Path workDir = baseDir.resolve("download");

        Files.createDirectories(workDir);

        Path workFile = Files.createFile(workDir.resolve("download.html"));

        try (final InputStream in = conn.getInputStream()) {
            Files.copy(in, workFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return workFile;
    }
}
