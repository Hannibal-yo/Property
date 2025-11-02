/*
 * Class: CMSC203
 * Instructor: Prof Ahmed Tarek
 * Description: Data element representing an axis-aligned rectangular plot.
 *              Coordinates (x, y) refer to the UPPER-LEFT corner.
 *              width is horizontal extent; depth is vertical extent.
 * Due: 10/29/2025
 * Platform/compiler: Java
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Hannibal Altasseb
 */

public class Property {

    private String propertyName;
    private String city;
    private double rentAmount;
    private String owner;
    private Plot plot;

    /** Default constructor: initializes fields with default values. */
    public Property() {
        this.propertyName = "";
        this.city = "";
        this.owner = "";
        this.rentAmount = 0.0;
        this.plot = new Plot(0, 0, 1, 1);
    }

    /** Copy constructor. */
    public Property(Property otherProperty) {
        if (otherProperty == null) {
            this.propertyName = "";
            this.city = "";
            this.owner = "";
            this.rentAmount = 0.0;
            this.plot = new Plot(0, 0, 1, 1);
        } else {
            this.propertyName = otherProperty.propertyName;
            this.city = otherProperty.city;
            this.owner = otherProperty.owner;
            this.rentAmount = otherProperty.rentAmount;
            this.plot = new Plot(otherProperty.plot);
        }
    }

    /** Constructor without plot data (defaults to 0,0,1,1). */
    public Property(String propertyName, String city, double rentAmount, String owner) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentAmount = rentAmount;
        this.owner = owner;
        this.plot = new Plot(0, 0, 1, 1);
    }

    /** Constructor including plot data. */
    public Property(String propertyName, String city, double rentAmount, String owner,
                    int x, int y, int width, int depth) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentAmount = rentAmount;
        this.owner = owner;
        this.plot = new Plot(x, y, width, depth);
    }

    // ---------------- Getters / Setters ----------------
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Plot getPlot() {
        return new Plot(plot); // defensive copy
    }

    public void setPlot(int x, int y, int width, int depth) {
        this.plot = new Plot(x, y, width, depth);
    }

    /** Returns formatted property info string (no spaces). */
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%.1f",
                propertyName, city, owner, rentAmount);
    }
}
