package se.kth.iv1350.repairelectricbike.model.discount;

/**
 * Applies a discount for customers making their first repair order.
 */
public class NewCustomerDiscount implements DiscountStrategy {

    /**
     * Calculates the discount for a new customer.
     *
     * @param context Contains information about the repair order.
     * @return The discount amount.
     */
    @Override
    public double calculateDiscount(DiscountContext context) {
        return context.getTotalPrice() * 0.50; 
    }


}
