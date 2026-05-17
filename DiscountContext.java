package se.kth.iv1350.repairelectricbike.integration;

import java.util.ArrayList;
import se.kth.iv1350.repairelectricbike.integration.exception.CustomerNotFoundException;
import se.kth.iv1350.repairelectricbike.integration.exception.DatabaseFailureException;
import se.kth.iv1350.repairelectricbike.model.Customer;
import se.kth.iv1350.repairelectricbike.model.dto.CustomerDTO;

/**
 * Stores customer data and handles customer lookups.
 * Only one customer registry exists during runtime.
 */
public class CustomerRegistry {

    private static final CustomerRegistry customerRegistry =
            new CustomerRegistry();

    private static final String DB_FAILURE_PHONE = "99999";

    private ArrayList<Customer> customers;

    /**
     * Creates a customer registry with some example customers.
     */
    private CustomerRegistry() {
        customers = new ArrayList<>();
        customers.add(new Customer("Oscar", "oscar@kth.com", "01234", "Cannondale", "SN345678", "Superior Pro"));
        customers.add(new Customer("Aleena", "aleena@kth.com", "05678", "Specialized", "SN987654", "Turbo Vado"));
        customers.add(new Customer("Emilia", "emilia@kth.com", "091011", "Trek", "SN234567", "Fuel EX"));
    }

    /**
     * Returns the shared customer registry.
     * @return The shared customer registry.
     */
    public static CustomerRegistry customerRegistry() {
        return customerRegistry;
    }

    /**
     * Finds a customer by phone number.
     *
     * @param phoneNumber The customer's phone number.
     * @return A {@link CustomerDTO} with the customer's information.
     * @throws CustomerNotFoundException If no customer with the specified
     *                                   phone number exists in the registry.
     * @throws DatabaseFailureException  If the database cannot be reached.
     *                                   This is simulated by searching for
     *                                   the phone number {@value #DB_FAILURE_PHONE}.
     */
    public CustomerDTO findCustomer(String phoneNumber)
            throws CustomerNotFoundException, DatabaseFailureException {
        if (phoneNumber.equals(DB_FAILURE_PHONE)) {
            throw new DatabaseFailureException(
                    "Could not connect to customer database when searching for: " + phoneNumber);
        }
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return new CustomerDTO(
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhoneNumber(),
                        customer.getBikeBrand(),
                        customer.getBikeSerialNo(),
                        customer.getBikeModel());
            }
        }
        throw new CustomerNotFoundException(phoneNumber);
    }
}