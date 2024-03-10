package com.movchan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        String fileName = System.getProperty("file", "10m.txt");
        ProcessFile processFile = new ProcessFile();
        long start = System.currentTimeMillis();
        processFile.processing(fileName);
        long end = System.currentTimeMillis() - start;
        LOGGER.info("Time :" + end/1000 + "s");
    }

}
