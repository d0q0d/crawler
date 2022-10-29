package com.github.alirezagolshan.crawler.util;

import java.util.logging.Logger;

public class Utils {

  private static final Logger logger = Logger.getLogger(Utils.class.getSimpleName());

  public static void uname() {
    logger.info(
            System.getProperty("line.separator") +
                    "-------------------------------------------------------------------" +
                    System.getProperty("line.separator") +
                    "| " +
                    System.getProperty("os.name") +
                    System.getProperty("line.separator") +
                    "| " +
                    System.getProperty("os.version") +
                    System.getProperty("line.separator") +
                    "| " +
                    System.getProperty("os.arch") +
                    System.getProperty("line.separator") +
                    "| " +
                    System.getProperty("java.home") +
                    System.getProperty("line.separator") +
                    "| " +
                    System.getProperty("java.version") +
                    System.getProperty("line.separator") +
                    "| " +
                    System.getProperty("user.home") +
                    System.getProperty("line.separator") +
                    "| " +
                    System.getProperty("user.name") +
                    System.getProperty("line.separator") +
                    "-------------------------------------------------------------------");
  }
}
