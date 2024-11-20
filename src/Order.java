import java.util.*;

public class Order {
    private final List<Flavor> flavors = new ArrayList<>();
    private final List<Topping> toppings = new ArrayList<>();
    private final boolean isWaffleCone;
    private static final double WAFFLE_CONE_PRICE = 5.00;
    private static final double TAX_RATE = 0.08;

    public Order(boolean waffleCone) {
        this.isWaffleCone = waffleCone;
    }

    public void addFlavor(Flavor flavor) {
        flavors.add(flavor);
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (Flavor flavor : flavors) {
            subtotal += flavor.getPricePerScoop();
        }
        for (Topping topping : toppings) {
            subtotal += topping.getPrice();
        }
        if (isWaffleCone) {
            subtotal += WAFFLE_CONE_PRICE;
        }
        return subtotal;
    }

    public double calculateTax() {
        return calculateSubtotal() * TAX_RATE;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax();
    }
}
