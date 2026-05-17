package se.kth.iv1350.repairelectricbike.model.discount;

public class DiscountFactory {
    
    
    public DiscountFactory() {
        
    }
    /**
    * Selects the appropriate discount strategy based on previous customer orders.
    *
    * @param context Contains information about previous orders and total price.
    * @return The selected discount strategy.
    */
    public DiscountStrategy selectDiscount(DiscountContext context) {
        int currentNrOfOrders = context.getNrOfPrevOrders() + 1;
        if (context.getNrOfPrevOrders() == 0) {
            return new NewCustomerDiscount(); 
        }
        if (currentNrOfOrders % 3 == 0){
            return new FrequentCustomerDiscount();
        }
        return new NoDiscount();

    }
}
