package se.kth.iv1350.repairelectricbike.model.discount;


/**
 * Applies a discount for frequent customers.
 */

public class FrequentCustomerDiscount implements DiscountStrategy {
    private static final double DISCOUNT_PERCENTAGE = 0.10;

    /**
     * The minimum number of previous orders required to be eligible for the discount.
     * @param context The context containing the number of previous orders and total cost.
     * @return The discount amount if the customer is eligible.
     */
    @Override
    public double calculateDiscount(DiscountContext context) {
            return context.getTotalPrice() * DISCOUNT_PERCENTAGE;
    }

}
