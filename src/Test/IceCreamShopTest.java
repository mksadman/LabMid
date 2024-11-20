package Test;

import main.Flavor;
import main.InvoiceGenerator;
import main.Order;
import main.Topping;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class IceCreamShopTest {

    @org.junit.Test
    public void testSubtotalCalculation() {
        Order order = new Order(false); // Paper cup
        order.addFlavor(Flavor.CHOCOLATE_FUDGE);
        order.addFlavor(Flavor.MINT_CHOCOLATE_CHIP);
        order.addTopping(Topping.CHOCOLATE_CHIPS);

        double expectedSubtotal = 3.00 + 2.80 + 0.50;

        assertEquals(expectedSubtotal, order.calculateSubtotal(), 0.01);
    }

    @org.junit.Test
    public void testTaxCalculation() {
        Order order = new Order(false); // Paper cup
        order.addFlavor(Flavor.STRAWBERRY_SWIRL);
        order.addTopping(Topping.FRESH_STRAWBERRIES);

        double expectedSubtotal = 2.75 + 1.00;
        double expectedTax = expectedSubtotal * 0.08;
        assertEquals(expectedTax, order.calculateTax(), 0.01);
    }

    @org.junit.Test
    public void testTotalPriceWithWaffleCone() {
        Order order = new Order(true); // Waffle cone
        order.addFlavor(Flavor.PISTACHIO_DELIGHT);
        order.addFlavor(Flavor.MINT_CHOCOLATE_CHIP);
        order.addTopping(Topping.SPRINKLES);

        double expectedSubtotal = 3.25 + 2.80 + 0.30 + Order.getWaffleConePrice();
        double expectedTotal = expectedSubtotal + (expectedSubtotal * 0.08);
        assertEquals(expectedTotal, order.calculateTotal(), 0.01);
    }

    @org.junit.Test
    public void testInvoiceGeneration() throws IOException {
        Order order = new Order(false); // Paper cup
        order.addFlavor(Flavor.CHOCOLATE_FUDGE);
        order.addTopping(Topping.MARSHMALLOWS);
        String fileName = "test_invoice.txt";

        InvoiceGenerator.generateInvoice(order, fileName);

        File invoiceFile = new File(fileName);
        assertTrue(invoiceFile.exists());

        String expectedContent = """
                Ice Cream Shop Invoice
                Chocolate Fudge - 1 scoop: $3.00
                Marshmallow - 1 time(s): $0.70
                Subtotal: $3.70
                Tax: $0.30
                Total Amount Due: $4.00
                """;
        String actualContent = Files.readString(invoiceFile.toPath());
        assertEquals(expectedContent.trim(), actualContent.trim());
    }

    @org.junit.Test
    public void testMultipleFlavorsAndToppings() {
        Order order = new Order(true); // Waffle cone
        order.addFlavor(Flavor.STRAWBERRY_SWIRL);
        order.addFlavor(Flavor.STRAWBERRY_SWIRL);
        order.addTopping(Topping.FRESH_STRAWBERRIES);
        order.addTopping(Topping.FRESH_STRAWBERRIES);

        double expectedSubtotal = (2.75 * 2) + (1.00 * 2) + Order.getWaffleConePrice();
        double expectedTotal = expectedSubtotal + (expectedSubtotal * 0.08);

        assertEquals(expectedSubtotal, order.calculateSubtotal(), 0.01);
        assertEquals(expectedTotal, order.calculateTotal(), 0.01);
    }
}
