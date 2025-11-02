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

public class Plot {
    // Upper-left corner
    private int x;
    private int y;

    // Horizontal and vertical extents
    private int width;
    private int depth;

    /** Default constructor: a minimal 1x1 plot at origin. */
    public Plot() {
        this(0, 0, 1, 1);
    }

    /** Copy constructor. */
    public Plot(Plot other) {
        if (other == null) {
            this.x = 0;
            this.y = 0;
            this.width = 1;
            this.depth = 1;
        } else {
            this.x = other.x;
            this.y = other.y;
            this.width = other.width;
            this.depth = other.depth;
        }
    }

    /** Full constructor. */
    public Plot(int x, int y, int width, int depth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.depth = depth;
    }

    // ---------------- Getters / Setters ----------------
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getDepth() { return depth; }
    public void setDepth(int depth) { this.depth = depth; }

    // ---------------- Geometry helpers ----------------
    private int left()   { return x; }
    private int right()  { return x + width; }   // exclusive boundary
    private int top()    { return y; }
    private int bottom() { return y + depth; }   // exclusive boundary

    /**
     * Returns true if this plot and the other plot overlap with positive area.
     * Touching edges/corners do NOT count as overlap (per assignment examples).
     */
    public boolean overlaps(Plot other) {
        if (other == null) return false;

        // Non-overlap conditions (edge-touch included as NON-overlap):
        if (this.right()  <= other.left())   return false; // this entirely left of other
        if (this.left()   >= other.right())  return false; // this entirely right of other
        if (this.bottom() <= other.top())    return false; // this entirely above other
        if (this.top()    >= other.bottom()) return false; // this entirely below other

        // Otherwise rectangles intersect with positive area → overlap
        return true;
    }

    /**
     * Returns true if this plot fully contains the other plot.
     * Inclusive on edges (edge-on-edge is considered contained).
     */
    public boolean encompasses(Plot other) {
        if (other == null) return false;

        boolean containsHorizontally = this.left()  <= other.left()  && this.right()  >= other.right();
        boolean containsVertically   = this.top()   <= other.top()   && this.bottom() >= other.bottom();

        return containsHorizontally && containsVertically;
    }

    /** String representation: x,y,width,depth (no spaces). */
    @Override
    public String toString() {
        return String.format("%d,%d,%d,%d", x, y, width, depth);
    }
}
