package se.kth.iv1350.repairelectricbike.controller;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.repairelectricbike.controller.exception.CustomerOperationFailedException;
import se.kth.iv1350.repairelectricbike.integration.CustomerRegistry;
import se.kth.iv1350.repairelectricbike.integration.Printer;
import se.kth.iv1350.repairelectricbike.integration.RegistryCreator;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairelectricbike.integration.exception.CustomerNotFoundException;
import se.kth.iv1350.repairelectricbike.integration.exception.DatabaseFailureException;
import se.kth.iv1350.repairelectricbike.model.RepairOrder;
import se.kth.iv1350.repairelectricbike.model.RepairOrderObserver;
import se.kth.iv1350.repairelectricbike.model.RepairTask;
import se.kth.iv1350.repairelectricbike.model.discount.*;
import se.kth.iv1350.repairelectricbike.model.dto.CustomerDTO;
import se.kth.iv1350.repairelectricbike.model.dto.RepairOrderDTO;


/**
 * The application controller. Receives all calls from the view and
 * delegates work to the model and integration layers.
 */
public class Controller {

    private RepairOrder currentRepairOrder;

    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;
    private final Printer printer;
    private final ErrorLogger errorLogger = new ErrorLogger();
    private final List<RepairOrderObserver> repairOrderObservers = new ArrayList<>();

    private int nextOrderId = 1;

    /**
     * Creates a new controller.
     *
     * @param registryCreator Provides access to all registries.
     * @param printer         The printer used to print accepted repair orders.
     */
    public Controller(RegistryCreator registryCreator, Printer printer) {
        this.customerRegistry = registryCreator.getCustomerRegistry();
        this.repairOrderRegistry = registryCreator.getRepairOrderRegistry();
        this.printer = printer;
    }

    /**
     * Registers an observer that will be notified whenever a repair order
     * is updated.
     *
     * @param observer The observer to register.
     */
    public void addRepairOrderObserver(RepairOrderObserver observer) {
        repairOrderObservers.add(observer);
    }

    /**
     * Searches for a customer in the customer registry using their phone number.
     *
     * @param phoneNumber The phone number of the customer to find.
     * @return A {@link CustomerDTO} with the customer's information.
     * @throws CustomerNotFoundException        If no customer with the given
     *                                          phone number was found.
     * @throws CustomerOperationFailedException If the database could not be reached.
     */
    public CustomerDTO findCustomer(String phoneNumber) throws CustomerNotFoundException {
        try {
            return customerRegistry.findCustomer(phoneNumber);
        } catch (DatabaseFailureException ex) {
            errorLogger.log(ex);
            throw new CustomerOperationFailedException(
                    "Failed to search for customer with phone: " + phoneNumber, ex);
        }
    }

    /**
     * Creates a new repair order, registers all observers, and saves it to the registry.
     *
     * @param problemDescr A description of the reported problem.
     * @param phoneNumber  The customer's phone number.
     * @param serialNo     The serial number of the bike.
     */
    public void createRepairOrder(String problemDescr, String phoneNumber, int serialNo) {
        currentRepairOrder = new RepairOrder(nextOrderId++, problemDescr, phoneNumber, serialNo);
        for (RepairOrderObserver observer : repairOrderObservers) {
            currentRepairOrder.addObserver(observer);
        }
        repairOrderRegistry.createRepairOrder(currentRepairOrder);
    }

    /**
     * Returns all stored repair orders.
     *
     * @return A list of {@link RepairOrderDTO} representing all repair orders.
     */
    public List<RepairOrderDTO> findAllRepairOrders() {
        return repairOrderRegistry.findAllRepairOrders();
    }


    /**
     * Accepts the repair order with the specified id, updates its state, and prints it.
     *
     * @param id The id of the repair order to accept.
     */
    public void acceptRepairOrder(int id) {
        if (currentRepairOrder != null && currentRepairOrder.getId() == id) {
            currentRepairOrder.setState("ACCEPTED");
            repairOrderRegistry.updateRepairOrder(currentRepairOrder);
            printer.printRepairOrder(currentRepairOrder.toString());
        }
    }

    /**
     * Rejects the repair order with the specified id and updates its state.
     *
     * @param id The id of the repair order to reject.
     */
    public void rejectRepairOrder(int id) {
        if (currentRepairOrder != null && currentRepairOrder.getId() == id) {
            currentRepairOrder.setState("REJECTED");
            repairOrderRegistry.updateRepairOrder(currentRepairOrder);
        }
    }

    /**
     * Adds a diagnostic result to the current repair order.
     *
     * @param id     The id of the repair order to update.
     * @param result A textual description of the diagnostic result.
     */
    public void addDiagnosticResult(int id, String result) {
        if (currentRepairOrder != null && currentRepairOrder.getId() == id) {
            currentRepairOrder.addDiagnosticResult(id, result);
            repairOrderRegistry.updateRepairOrder(currentRepairOrder);
        }
    }

    /**
     * Adds a repair task to the current repair order.
     *
     * @param id   The id of the repair order to update.
     * @param task A textual description of the repair task.
     * @param cost The cost of the repair task.
     */
    public void addRepairTask(int id, String task, double cost) {
        if (currentRepairOrder != null && currentRepairOrder.getId() == id) {
            RepairTask repairTaskToAdd = new RepairTask("Repair Task", "Description: " + task, cost, "NEW");
            currentRepairOrder.addRepairTask(repairTaskToAdd);
            repairOrderRegistry.updateRepairOrder(currentRepairOrder);
        }
    }
    /**
    * Calculates the final repair price after applying discounts.
    *
    * @param id The id of the repair order.
    * @return The final discounted repair price.
    */
    public double calculateRepairOrderPrice(int id){
        if(isCorrectRepairOrder(id)){
            
            double totalPrice = currentRepairOrder.getTotalCost();

            int nrOfPrevOrders = repairOrderRegistry.countCustomerOrders(currentRepairOrder.getCustomerPhone()) - 1;
            
            DiscountContext discountContext = new DiscountContext(totalPrice, nrOfPrevOrders);

            DiscountFactory factory = new DiscountFactory();
            DiscountStrategy strategy = factory.selectDiscount(discountContext);

            PriceCalculator calculator = new PriceCalculator(strategy);
            return calculator.calculateDiscountedPrice(discountContext);
        }
        return 0.0;

    }

    /**
    * Checks if the specified id matches the current repair order.
    *
    * @param id The repair order id to check.
    * @return True if the repair order exists and ids match.
    */
    private boolean isCorrectRepairOrder(int id){
        return currentRepairOrder != null && currentRepairOrder.getId() == id;
    }

}
