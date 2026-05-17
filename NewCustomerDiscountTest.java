package se.kth.iv1350.repairelectricbike.model.discount;

public class PriceCalculator {
     private final DiscountStrategy strategy;
    
    /**
     * Creates a calculator using the specified discount strategy.
     * 
     */
    public PriceCalculator(DiscountStrategy chosenStrategy){
        this.strategy  = chosenStrategy;
    }
    
    /**
     * Calculates the final repair price after discount.
     *
     * @param context Contains pricing and customer order information.
     * @return The final price after discount.
     */
    public double calculateDiscountedPrice(DiscountContext context){
        double discountAmount = strategy.calculateDiscount(context);
        double finalPrice = context.getTotalPrice() - discountAmount;
        return finalPrice;
    }



}
