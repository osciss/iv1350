package se.kth.iv1350.repairelectricbike.model;

/**
 * A listener interface for receiving notifications about updated repair orders.
 * The class that is interested in such notifications implements this interface,
 * and the object created with that class is registered with
 * {@link RepairOrder#addObserver(RepairOrderObserver)}.
 * When a repair order is updated, that object's
 * {@link #repairOrderUpdated(RepairOrder)} method is invoked.
 */
public interface RepairOrderObserver {

    /**
     * Invoked when a repair order has been updated in any way.
     *
     * @param repairOrder The repair order that was updated.
     */
    void repairOrderUpdated(RepairOrder repairOrder);
}