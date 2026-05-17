package se.kth.iv1350.repairelectricbike.model.discount;

/**
 * Contains the information needed when calculating discounts.
 */
public class DiscountContext {
    private final double totalPrice;
    private final int nrOfPrevOrders;

    /**
     * Creates a new discount context.
     *
     * @param totalPrice The total repair price before discount.
     * @param nrOfPrevOrders The number of previous repair orders.
     */

    public DiscountContext(double totalPrice, int nrOfPrevOrders) {
        this.totalPrice = totalPrice;
        this.nrOfPrevOrders = nrOfPrevOrders;
    }
    
    /**
     * @return The total repair price before discount.
     */
    public double getTotalPrice() {
        return totalPrice;
    }
    
    /**
     * @return The number of previous repair orders.
     */
    public int getNrOfPrevOrders() {
        return nrOfPrevOrders;
    }


}
