package se.kth.iv1350.repairelectricbike.model.dto;

import java.time.LocalDate;

/**
 * Contains repair order information and is used to transfer data between layers.
 */
public class RepairOrderDTO {

    public final int id;
    public final LocalDate date;
    public final String problemDesc;
    public final String state;
    public final String customerPhoneNumber;
    public final String bikeSerialNo;

    /**
     * Creates a new RepairOrderDTO.
     *
     * @param id Identifier of the repair order.
     * @param date The date when the repair order was created.
     * @param problemDesc The description of the problem.
     * @param state The current state of the repair order.
     */
    public RepairOrderDTO(int id, LocalDate date, String problemDesc, String state, String customerPhoneNumber, String bikeSerialNo) {
        this.id = id;
        this.date = date;
        this.problemDesc = problemDesc;
        this.state = state;
        this.customerPhoneNumber = customerPhoneNumber;
        this.bikeSerialNo = bikeSerialNo;
    }
}