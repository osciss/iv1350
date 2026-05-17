package se.kth.iv1350.repairelectricbike.model.discount;

/**
 * Represents a discount strategy that gives no discount.
 */
public class NoDiscount implements DiscountStrategy {

    /**
     * Returns zero discount.
     *
     * @param context Contains information about the repair order.
     * @return Always returns 0.
     */
    @Override
    public double calculateDiscount(DiscountContext context) {
        return 0;
    }

}
