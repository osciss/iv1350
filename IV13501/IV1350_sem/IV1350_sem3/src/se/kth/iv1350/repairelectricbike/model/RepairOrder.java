package se.kth.iv1350.repairelectricbike.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a repair order in the system.
 */
public class RepairOrder {

    private int orderID;
    private LocalDate date;
    private String problemDescr;
    private String state;

    private String customerPhoneNumber;
    private String bikeSerialNo;

    private List<String> repairTasks = new ArrayList<>();
    private List<String> diagnosticResults = new ArrayList<>();

    /**
     * Creates a new repair order.
     *
     * @param orderID The ID of the repair order.
     * @param problemDescr The problem description.
     */
    public RepairOrder(int orderID, String problemDescr, String customerPhoneNumber, String bikeSerialNo) {
        this.orderID = orderID;
        this.date = LocalDate.now();
        this.problemDescr = problemDescr;
        this.state = "NEWLY CREATED";
        this.customerPhoneNumber = customerPhoneNumber;
        this.bikeSerialNo = bikeSerialNo;
    }

    /**
     * Returns a string representation of the repair order.
     *
     * @return A formatted string with repair order details.
     */
    public String toString() {
        return "Repair Order ID: " + orderID + "\n"
                + "Date: " + date + "\n"
                + "Problem: " + problemDescr + "\n"
                + "State: " + state + "\n"
                + "Customer Phone: " + customerPhoneNumber + "\n"
                + "Bike Serial No: " + bikeSerialNo + "\n"
                + "Repair Tasks: " + repairTasks + "\n"
                + "Diagnostic Results: " + diagnosticResults;
    }

    /**
     * Updates the state of the repair order.
     *
     * @param state The new state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Adds a diagnostic result.
     *
     * @param id The repair order ID.
     * @param result The diagnostic result.
     */
    public void addDiagnosticResult(int id, String result) {
        diagnosticResults.add("ID: " + id + " - " + result);
    }

    /**
     * Adds a repair task.
     *
     * @param task The repair task description.
     */
    public void addRepairTask(String task) {
        repairTasks.add(task);
    }

    /**
     * @return The repair order ID.
     */
    public int getId() {
        return orderID;
    }

    /**
     * @return The problem description.
     */
    public String getProblemDesc() {
        return problemDescr;
    }

    /**
     * @return The current state.
     */
    public String getState() {
        return state;
    }

    /**
     * @return The date when the order was created.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return The customer phone number associated with this repair order.
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * @return The bike serial number associated with this repair order.
     */
    public String getBikeSerialNo() {
        return bikeSerialNo;
    }
}