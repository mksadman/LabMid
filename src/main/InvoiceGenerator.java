package main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class InvoiceGenerator {
    public static void generateInvoice(Order order, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Ice Cream Shop Invoice\n");

            Map<Flavor, Integer> flavorCount = new HashMap<>();
            for (Flavor flavor : order.getFlavors()) {
                flavorCount.put(flavor, flavorCount.getOrDefault(flavor, 0) + 1);
            }

            Map<Topping, Integer> toppingCount = new HashMap<>();
            for (Topping topping : order.getToppings()) {
                toppingCount.put(topping, toppingCount.getOrDefault(topping, 0) + 1);
            }

            for (Map.Entry<Flavor, Integer> entry : flavorCount.entrySet()) {
                Flavor flavor = entry.getKey();
                int quantity = entry.getValue();
                double totalCost = flavor.getPricePerScoop() * quantity;
                writer.write(String.format("%s - %d scoop(s): $%.2f\n", flavor.name(), quantity, totalCost));
            }

            for (Map.Entry<Topping, Integer> entry : toppingCount.entrySet()) {
                Topping topping = entry.getKey();
                int quantity = entry.getValue();
                double totalCost = topping.getPrice() * quantity;
                writer.write(String.format("%s - %d time(s): $%.2f\n", topping.name(), quantity, totalCost));
            }

            if (order.isWaffleCone()) {
                writer.write(String.format("Waffle Cone: $%.2f\n", Order.getWaffleConePrice()));
            }

            double subtotal = order.calculateSubtotal();
            double tax = order.calculateTax();
            double total = order.calculateTotal();
            writer.write(String.format("Subtotal: $%.2f\n", subtotal));
            writer.write(String.format("Tax: $%.2f\n", tax));
            writer.write(String.format("Total Amount Due: $%.2f\n", total));
        } catch (IOException e) {
            System.out.println("Error writing invoice: " + e.getMessage());
        }
    }
}
