package se.kth.iv1350.repairelectricbike.model.discount;

public interface DiscountStrategy {
   /**
     * Calculates the discount amount.
     *
     * @param context Information needed to calculate the discount.
     * @return The discount amount.
     */
    double calculateDiscount(DiscountContext context);

}
