package se.kth.iv1350.repairelectricbike.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.repairelectricbike.model.RepairOrder;
import se.kth.iv1350.repairelectricbike.model.dto.RepairOrderDTO;

/**
 * Stores repair orders in the system.
 * There is only one shared registry exists during runtime.
 */
public class RepairOrderRegistry {

    private static final RepairOrderRegistry sharedRepairOrders =
            new RepairOrderRegistry();

    private List<RepairOrder> repairOrders = new ArrayList<>();

    /**
     *Creates shared repair order registry.
     */
    private RepairOrderRegistry() {
    }
    
    /**
     * Clears the repair order registry.
    */
    public void removeAllRepairOrders() {
        repairOrders.clear();
    }

    /**
     * Returns the shared repair order registry.
     * @return The shared repair order registry.
     */
    public static RepairOrderRegistry sharedRepairOrders() {
        return sharedRepairOrders;
    }

    /**
     * Adds a new repair order.
     *
     * @param repairOrder The repair order to add.
     */
    public void createRepairOrder(RepairOrder repairOrder) {
        repairOrders.add(repairOrder);
    }

    /**
     * Updates an existing repair order.
     *
     * @param repairOrder The repair order to update.
     */
    public void updateRepairOrder(RepairOrder repairOrder) {
        for (int i = 0; i < repairOrders.size(); i++) {
            if (repairOrders.get(i).getId() == repairOrder.getId()) {
                repairOrders.set(i, repairOrder);
                return;
            }
        }
    }

    /**
     * Finds a repair order by ID.
     *
     * @param id The repair order ID.
     * @return The repair order, or null if not found.
     */
    public RepairOrder findRepairOrder(int id){
        for(RepairOrder order : repairOrders){
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }

    /**
     * Returns all repair orders as DTOs.
     * @return List of RepairOrderDTOs
     */
    public List<RepairOrderDTO> findAllRepairOrders() {
        List<RepairOrderDTO> result = new ArrayList<>();

        for (RepairOrder order : repairOrders) {
            result.add(new RepairOrderDTO(
                    order.getId(),
                    order.getDate(),
                    order.getProblemDesc(),
                    order.getState()
            ));
        }
        return result;
    }
    /**
     * Counts the number of repair orders for a given customer phone number.
     *
     * @param customerPhone The customer's phone number.
     * @return The number of repair orders associated with the given phone number.
     */
    public int countCustomerOrders(String customerPhone){
        int count = 0;
        for(RepairOrder order : repairOrders){
            if(order.getCustomerPhone().contains(customerPhone)){
                count++;
            }
        }
        return count;
    }
}