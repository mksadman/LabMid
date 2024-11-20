public class Main {
    public static void main(String[] args) {
        Order order = new Order(true);
        order.addFlavor(Flavor.CHOCOLATE_FUDGE);
        order.addFlavor(Flavor.MINT_CHOCOLATE_CHIP);
        order.addTopping(Topping.CHOCOLATE_CHIPS);
        order.addTopping(Topping.FRESH_STRAWBERRIES);

        InvoiceGenerator.generateInvoice(order, "invoice.txt");

        Order order1 = new Order(false);
        order1.addFlavor(Flavor.PISTACHIO_DELIGHT);
        order1.addFlavor(Flavor.STRAWBERRY_SWIRL);
        order1.addTopping(Topping.MARSHMALLOWS);
        order1.addTopping(Topping.SPRINKLES);

        InvoiceGenerator.generateInvoice(order1, "invoice1.txt");
    }
}
