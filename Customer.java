package se.kth.iv1350.repairelectricbike.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Logs error messages to a file. Used to notify developers when
 * the program is not functioning as intended.
 */
public class ErrorLogger {

    private static final String LOG_FILE = "error-log.txt";

    /**
     * Logs the specified exception to the error log file, including
     * a timestamp and the full stack trace.
     *
     * @param ex The exception to log.
     */
    public void log(Exception ex) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("[" + LocalDateTime.now() + "] ERROR: " + ex.getMessage());
            ex.printStackTrace(writer);
            writer.println();
        } catch (IOException ioEx) {
            System.err.println("WARNING: Could not write to error log: " + ioEx.getMessage());
        }
    }
}