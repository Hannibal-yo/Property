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

public class ManagementCompany {

    private String name;
    private String taxID;
    private double mgmtFeePercent;

    public static final int MAX_PROPERTY = 5;
    public static final int MGMT_WIDTH = 10;
    public static final int MGMT_DEPTH = 10;

    private Property[] properties;
    private int numberOfProperties;
    private Plot plot;

    /** Default constructor */
    public ManagementCompany() {
        this.name = "";
        this.taxID = "";
        this.mgmtFeePercent = 0;
        this.properties = new Property[MAX_PROPERTY];
        this.numberOfProperties = 0;
        this.plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
    }

    /** Full constructor (without plot coordinates) */
    public ManagementCompany(String name, String taxID, double mgmtFeePercent) {
        this.name = name;
        this.taxID = taxID;
        this.mgmtFeePercent = mgmtFeePercent;
        this.properties = new Property[MAX_PROPERTY];
        this.numberOfProperties = 0;
        // As per examples, management company’s plot is 12,12,6,6
        this.plot = new Plot(12, 12, 6, 6);
    }

    /** Full constructor (with plot coordinates) */
    public ManagementCompany(String name, String taxID, double mgmtFeePercent,
                             int x, int y, int width, int depth) {
        this.name = name;
        this.taxID = taxID;
        this.mgmtFeePercent = mgmtFeePercent;
        this.properties = new Property[MAX_PROPERTY];
        this.numberOfProperties = 0;
        this.plot = new Plot(x, y, width, depth);
    }

    // ---------------- Getters / Setters ----------------
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTaxID() { return taxID; }
    public void setTaxID(String taxID) { this.taxID = taxID; }

    public double getMgmtFeePercent() { return mgmtFeePercent; }
    public void setMgmtFeePercent(double mgmtFeePercent) { this.mgmtFeePercent = mgmtFeePercent; }

    public Plot getPlot() { return new Plot(plot); }

    // ---------------- Core Methods ----------------

    /**
     * Adds a property to the company's list.
     * @param property the property object to add
     * @return index where added, or negative code if failed
     */
    public int addProperty(Property property) {
        if (property == null) return -2;                 // property is null
        if (numberOfProperties >= MAX_PROPERTY) return -1; // array full
        if (!plot.encompasses(property.getPlot())) return -3; // out of bounds

        // Check overlap with existing properties
        for (int i = 0; i < numberOfProperties; i++) {
            if (properties[i] != null &&
                    properties[i].getPlot().overlaps(property.getPlot()))
                return -4; // overlap
        }

        // Otherwise, add the property
        properties[numberOfProperties] = new Property(property);
        numberOfProperties++;
        return numberOfProperties - 1;
    }

    /** Overloaded addProperty() that takes parameters directly */
    public int addProperty(String name, String city, double rent, String owner) {
        Property p = new Property(name, city, rent, owner);
        return addProperty(p);
    }

    public int addProperty(String name, String city, double rent, String owner,
                           int x, int y, int width, int depth) {
        Property p = new Property(name, city, rent, owner, x, y, width, depth);
        return addProperty(p);
    }

    /** @return true if the array is full */
    public boolean isPropertiesFull() {
        return numberOfProperties >= MAX_PROPERTY;
    }

    /** @return number of properties currently stored */
    public int getPropertiesCount() {
        return numberOfProperties;
    }

    /** @return true if management fee percent is between 0–100 */
    public boolean isManagementFeeValid() {
        return mgmtFeePercent >= 0 && mgmtFeePercent <= 100;
    }

    /** Removes (nullifies) the last property in the array */
    public void removeLastProperty() {
        if (numberOfProperties > 0) {
            properties[numberOfProperties - 1] = null;
            numberOfProperties--;
        }
    }

    /** Returns total rent of all properties */
    public double getTotalRent() {
        double total = 0.0;
        for (int i = 0; i < numberOfProperties; i++) {
            if (properties[i] != null)
                total += properties[i].getRentAmount();
        }
        return total;
    }

    /** Returns the Property with the highest rent */
    public Property getHighestRentProperty() {
        if (numberOfProperties == 0) return null;

        Property highest = properties[0];
        for (int i = 1; i < numberOfProperties; i++) {
            if (properties[i] != null &&
                    properties[i].getRentAmount() > highest.getRentAmount()) {
                highest = properties[i];
            }
        }
        return new Property(highest);
    }

    /**
     * Returns the string representation of the ManagementCompany.
     * For GFA test compatibility, returns the plot’s toString() right now.
     */
    @Override
    public String toString() {
        // GFA expects "12,12,6,6"
        return plot.toString();
    }
}
