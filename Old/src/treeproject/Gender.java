package treeproject;

public enum Gender {
    /** Male. */
    Male("Male"),
    /** Female. */
    Female("Female"),
    /** Gender is not known, or not specified. */
    UNKNOWN("Unknown");

    private final String name;

    private Gender(String name) {
        this.name = name;
    }

    /**
     * @return The string representation of this element in the enumeration.
     */
    public String getName() {
        return this.name;
    }
}