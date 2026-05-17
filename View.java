package se.kth.iv1350.repairelectricbike.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a repair order in the system. Observers can be registered
 * to receive notifications whenever this repair order is updated.
 */
public class RepairOrder {

    private final int orderID;
    private final String customerPhone;
    private final int bikeSerialNo;
    private LocalDate date;
    private String problemDescr;
    private String state;

    private List<RepairTask> repairTasks = new ArrayList<>();
    private List<String> diagnosticResults = new ArrayList<>();
    private List<RepairOrderObserver> observers = new ArrayList<>();

    /**
     * Creates a new repair order.
     *
     * @param orderID The ID of the repair order.
     * @param problemDescr The problem description.
     * @param customerPhone The customer's phone number.
     * @param bikeSerialNo The bike serial number.
     */
    public RepairOrder(int orderID, String problemDescr, String customerPhone, int bikeSerialNo) {
        this.orderID = orderID;
        this.date = LocalDate.now();
        this.problemDescr = problemDescr;
        this.customerPhone = customerPhone;
        this.bikeSerialNo = bikeSerialNo;
        this.state = "NEWLY CREATED";
    }

    /**
     * Registers an observer that will be notified whenever this repair order
     * is updated.
     *
     * @param observer The observer to register.
     */
    public void addObserver(RepairOrderObserver observer) {
        observers.add(observer);
    }

    /**
     * Updates the state of the repair order and notifies all observers.
     *
     * @param state The new state.
     */
    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    /**
     * Adds a diagnostic result and notifies all observers.
     *
     * @param id     The repair order ID.
     * @param result The diagnostic result.
     */
    public void addDiagnosticResult(int id, String result) {
        diagnosticResults.add("ID: " + id + " - " + result);
        notifyObservers();
    }

    /**
     * Adds a repair task and notifies all observers.
     *
     * @param task The repair task description.
     */
    public void addRepairTask(RepairTask task) {
        repairTasks.add(task);
        notifyObservers();
    }

    /**
     * Returns the repair order ID.
     *
     * @return The repair order ID.
     */
    public int getId() {
        return orderID;
    }

    /**
     * Returns the customer's phone number.
     *
     * @return The customer's phone number.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Returns the bike serial number.
     *
     * @return The bike serial number.
     */
    public int getBikeSerialNo() {
        return bikeSerialNo;
    }

    /**
     * Returns the problem description.
     *
     * @return The problem description.
     */
    public String getProblemDesc() {
        return problemDescr;
    }

    /**
     * Returns the current state.
     *
     * @return The current state.
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the date when the order was created.
     *
     * @return The creation date.
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Calculates the total cost of all repair tasks.
     *
     * @return The total cost of all repair tasks.
     */
    public double getTotalCost() {
        double totalCost = 0;

        for (RepairTask task : repairTasks) {
            totalCost += task.getCost();
        }

        return totalCost;
    }

    /**
     * Returns a string representation of the repair order.
     *
     * @return A formatted string with repair order details.
     */
    @Override
    public String toString() {
        return "Repair Order ID: " + orderID + "\n"
                + "Date: " + date + "\n"
                + "Problem: " + problemDescr + "\n"
                + "State: " + state + "\n"
                + "Repair Tasks: " + repairTasks + "\n"
                + "Diagnostic Results: " + diagnosticResults;
    }

    private void notifyObservers() {
        for (RepairOrderObserver observer : observers) {
            observer.repairOrderUpdated(this);
        }
    }
    
}
