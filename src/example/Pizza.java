package example;

/** Project:
 * Purpose Details: Represents a Pizza object for Lab 4 serialization and messaging.
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 03/31/2026
 * Last Date Changed: 03/31/2026
 * Rev: 1
 */
public class Pizza {

    /**
     * The size of the pizza.
     */
    private String size;

    /**
     * The crust type of the pizza.
     */
    private String crust;

    /**
     * The topping of the pizza.
     */
    private String topping;

    /**
     * The price of the pizza.
     */
    private double price;

    /**
     * Default constructor for Pizza.
     */
    public Pizza() {
    }

    /**
     * Constructs a Pizza object with all fields.
     *
     * @param size The pizza size.
     * @param crust The crust type.
     * @param topping The topping.
     * @param price The pizza price.
     */
    public Pizza(String size, String crust, String topping, double price) {
        this.size = size;
        this.crust = crust;
        this.topping = topping;
        this.price = price;
    }

    /**
     * Gets the size of the pizza.
     *
     * @return The pizza size.
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the pizza.
     *
     * @param size The pizza size.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the crust type.
     *
     * @return The crust type.
     */
    public String getCrust() {
        return crust;
    }

    /**
     * Sets the crust type.
     *
     * @param crust The crust type.
     */
    public void setCrust(String crust) {
        this.crust = crust;
    }

    /**
     * Gets the topping.
     *
     * @return The topping.
     */
    public String getTopping() {
        return topping;
    }

    /**
     * Sets the topping.
     *
     * @param topping The topping.
     */
    public void setTopping(String topping) {
        this.topping = topping;
    }

    /**
     * Gets the price of the pizza.
     *
     * @return The pizza price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the pizza.
     *
     * @param price The pizza price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the Pizza object.
     *
     * @return A formatted pizza string.
     */
    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", crust='" + crust + '\'' +
                ", topping='" + topping + '\'' +
                ", price=" + price +
                '}';
    }
}