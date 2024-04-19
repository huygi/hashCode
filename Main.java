import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l","He","lo");
        String dText = "He".concat("llo");
        String eText = "hello";

        List<String> hellos = Arrays.asList(aText, bText, cText, dText, eText);

        // Print each of these Strings out, along with their hashCodes. 
        hellos.forEach( s -> System.out.println(s + ": " + s.hashCode()));

        // HashSet
        Set<String> mySet = new HashSet<>(hellos);
        System.out.println("mySet = " + mySet );
        System.out.println("# of elements = " + mySet.size());

        // Loop through the elements and see which String references are really in this Set.
        for( String setValue : mySet ) {
            System.out.print(setValue + ": ");
            for( int i = 0; i < hellos.size(); i++ ) {
                if( setValue == hellos.get(i)) {
                    System.out.print(i + ", ");
                }
            }
            System.out.println(" ");
        }

        PlayingCard aceHearts = new PlayingCard( "Heart","Ace");
        PlayingCard kingClubs = new PlayingCard( "Clubs", "King");
        PlayingCard queenSpades = new PlayingCard ("Spades", "Queen");
        // Get unique hash codes for each.
        List<PlayingCard> cards = Arrays.asList( aceHearts, kingClubs, queenSpades );
        cards.forEach( s -> System.out.println(s + ": " + s.hashCode()));

        // Create a set of cards, and add the cards one at a time.
        // Set don't allow duplicate
        // Order will not be sorted
        Set<PlayingCard> deck = new HashSet<>();
        for ( PlayingCard c : cards ) {
            if( !deck.add(c) ) {                                        // Add method return true if the element successfully added
                System.out.println( "Found a duplicate for " + c );
            }
        }
        System.out.println(deck);
    }
}