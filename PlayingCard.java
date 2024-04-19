public class PlayingCard {
    private String suit;
    private String face;
    private int internalHash;
    public PlayingCard( String suit, String face ) {
        this.suit = suit;
        this.face = face;
        this.internalHash = (suit.equals("Heart")) ? 11 : 12;
    }
    @Override
    public String toString() {
        return face + " of " + suit;
    }

//    @Override
//    public int hashCode() {
//        return internalHash;
//    }
//
//    // Return true, so that all my objects will be equal no matter what.
//    // Will print the statement if found duplicate
//    // The only time when the equals method is called when adding elements to the hash set, is when the hashCode algorithm returns the same bucket identifier.
//    @Override
//    public boolean equals( Object obj ) {
//
//        System.out.println("--> Checking Playing Card equality");
//        return true;
//    }

    //hashCode() and equals() method default implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Equals method first checks for equal references, because two references pointing to the same object is the surest test of equality.
        if (o == null || getClass() != o.getClass()) return false;  // Compare the result of the getClass methods on both objects,

        PlayingCard that = (PlayingCard) o; // If objects are equal, it will case the object passed, to a PlayingCard.

        if (!suit.equals(that.suit)) return false;  //Check the suit for equal
        return face.equals(that.face);              // Then check the face for equal
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();           // Starts with the hashCode for the string for the suit.
        result = 31 * result + face.hashCode(); // It then multiply by 31 (small prime number), and adding the hash code for the String
        return result;
    }
}
