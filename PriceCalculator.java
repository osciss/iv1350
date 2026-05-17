package se.kth.iv1350.repairelectricbike.integration.exception;

/**
 * Thrown when a call to the database fails. This is an unchecked exception
 * since a database failure cannot be recovered from by the caller, and
 * forcing every caller to catch it would clutter the code unnecessarily.
 */
public class DatabaseFailureException extends RuntimeException {

    /**
     * Creates a new instance with a message describing what went wrong.
     *
     * @param msg A message describing the database failure.
     */
    public DatabaseFailureException(String msg) {
        super(msg);
    }

    /**
     * Creates a new instance with a message and the original cause.
     *
     * @param msg   A message describing the database failure.
     * @param cause The original exception that caused this failure.
     */
    public DatabaseFailureException(String msg, Exception cause) {
        super(msg, cause);
    }
}