package io.github.brushup.decksservice.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.servererrors.InvalidQueryException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CqlFileUtils {
    
    /** Helper for CQL FILE. */
    private static final String UTF8_ENCODING         = "UTF-8";
    private static final String NEW_LINE              = System.getProperty("line.separator");
    
    private static String loadFileAsString(String fileName)
    throws FileNotFoundException {
        InputStream in = CqlFileUtils.class.getResourceAsStream(fileName);
        if (in == null) {
            // Fetch absolute classloader path
            in =  CqlFileUtils.class.getClassLoader().getResourceAsStream(fileName);
        }
        if (in == null) {
            // Thread
            in =  Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        }
        if (in == null) {
            throw new FileNotFoundException("Cannot load file " + fileName + " please check");
        }
        Scanner currentScan = null;
        StringBuilder strBuilder = new StringBuilder();
        try {
            currentScan = new Scanner(in, UTF8_ENCODING);
            while (currentScan.hasNextLine()) {
                strBuilder.append(currentScan.nextLine());
                strBuilder.append(NEW_LINE);
            }
        } finally {
            if (currentScan != null) {
                currentScan.close();
            }
        }
        return strBuilder.toString();
    }
    
    public static void executeCQLFile(CqlSession cqlSession, String fileName)
    throws FileNotFoundException {
        long top = System.currentTimeMillis();
        Arrays.stream(loadFileAsString(fileName).split(";")).forEach(statement -> {
            String query = statement.replaceAll(NEW_LINE, "").trim();
            try {
                if (query.length() > 0) {
                    cqlSession.execute(query);
                    log.info(" + Executed. " + query);
                }
            } catch (InvalidQueryException e) {
                log.warn(" + Query Ignore. " + query, e);
            }
        });
        log.info("Execution done in {} millis.", System.currentTimeMillis() - top);
    }

}