package com.wheany;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

public class Util {
    private static final Logger logger = Logger.getLogger(Util.class.getName());
    private static final Random rng = new Random();
    private static final int PATH_COMPONENT_LENGTH = 2;
    private static final int MIN_PATH_LENGTH = 6;
    private static final int NUM_RETRIES = 10;
    private static final String WORK_PATH_PREFIX = "rssifier-work";

    @NotNull
    public static String[] splitString(@NotNull String source, int length) {
        ArrayList<String> strings = new ArrayList<>(source.length()/length);

        for(int start = 0; start < source.length(); start += PATH_COMPONENT_LENGTH) {
            strings.add(source.substring(start, start + length));
        }

        return strings.toArray(new String[strings.size()]);
    }

    public static Path makeWorkDir() {
        synchronized (rng) {
            String pathCandidateString;
            int  retryPathGeneration = NUM_RETRIES;
            do {
                retryPathGeneration--;

                pathCandidateString = new BigInteger(64, rng).toString(Character.MAX_RADIX);
                if (pathCandidateString.length() < MIN_PATH_LENGTH) {
                    logger.fine(String.format("path candidate %s too short, retries left %d",
                            pathCandidateString,
                            retryPathGeneration));
                    continue;
                }
                String[] pathParts = splitString(pathCandidateString.substring(0, MIN_PATH_LENGTH), PATH_COMPONENT_LENGTH);

                Path workPathCandidate = Paths.get(WORK_PATH_PREFIX, pathParts);
                if(Files.exists(workPathCandidate)) {
                    logger.fine(String.format("path candidate %s exists, retries left %d",
                            workPathCandidate.toString(),
                            retryPathGeneration));
                    continue;
                }
                try {
                    Files.createDirectories(workPathCandidate);
                } catch (IOException e) {
                    logger.warning(String.format("Unable to create workDir %s: %s, retries left %d",
                            workPathCandidate.toString(),
                            e.getMessage(),
                            retryPathGeneration));
                    continue;
                }
                return workPathCandidate;
            } while (retryPathGeneration > 0);
        }
        return null;
    }
}
