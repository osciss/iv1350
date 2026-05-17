package se.kth.iv1350.repairelectricbike.controller.exception;

/**
 * Thrown by the controller when a customer operation fails due to a
 * technical error in the underlying systems. This exception wraps
 * lower-level exceptions so that the view does not depend on the
 * integration layer.
 */
public class CustomerOperationFailedException extends RuntimeException {

    /**
     * Creates a new instance with a message and the original cause.
     *
     * @param msg   A message describing what went wrong.
     * @param cause The original exception that caused this failure.
     */
    public CustomerOperationFailedException(String msg, Exception cause) {
        super(msg, cause);
    }
}