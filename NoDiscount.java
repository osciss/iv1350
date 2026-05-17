package se.kth.iv1350.repairelectricbike.integration.exception;

/**
 * Thrown when a search is made for a phone number that does not exist
 * in the customer registry. This is a checked exception since it represents
 * a business rule violation that the caller can reasonably handle.
 */
public class CustomerNotFoundException extends Exception {

    private final String phoneNumber;

    /**
     * Creates a new instance for the specified phone number.
     *
     * @param phoneNumber The phone number that was not found in the registry.
     */
    public CustomerNotFoundException(String phoneNumber) {
        super("No customer found with phone number: " + phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the phone number that caused this exception.
     *
     * @return The phone number that was not found.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}