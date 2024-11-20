package main;

public enum Topping {
    SPRINKLES(0.30),
    MARSHMALLOWS(0.70),
    CRUSHED_OREO(0.85),
    FRESH_STRAWBERRIES(1.00),
    CHOCOLATE_CHIPS(0.50);

    private final double price;
    Topping(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
