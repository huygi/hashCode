# **Understanding the importance of the hash code**

## Introduction
- HashSet and HashMap, are based on the hash codes of objects. 
- Since sets are unique because they don't support duplicates, adding an element always incurs the cost of first checking for a match. 
- If your set is large or very large, this becomes a costly operation, [O(n)], or linear time, if you remember the Big O Notation. 
- A mechanism to reduce this cost, is introduced by something called hashing. 
- If we created two buckets of elements, and the element could consistently identify which bucket it was stored in, then the lookup could be reduced by half. 
- If we created four buckets, we could reduce the cost by a quarter. 

## Hash Collection
- A hashed collection will optimally create a limited set of buckets, to provide an even distribution of the objects across the buckets in a full set. 
- A hash code can be any valid integer, so it could be one of 4.2 billion valid numbers. 
- If your collection only contains 100,000 elements, you don't want to back it with a storage mechanism of 4 billion possible placeholders. 
- And you don't want to have to iterate through 100000 elements one at a time to find a match or duplicate. 
- A hashing mechanism will take an integer hash code, and a capacity declaration which specifies the number of buckets to distribute the objects over. 
- It then translates the range of hash codes into a range of bucket identifiers. 
- Hashed implementations use a combination of hash code and other means, to provide the most efficient bucketing system, to achieve this desired uniform distribution of the objects. 

## Hashing in Java
- To understanding hashing in Java, we need to first understand the equality of objects. 
- There are two methods on java.util.Object, that all objects inherit. 
- **Testing for equality**
    - ```public boolean``` equals (Object obj)

- **The hashcode method**
    - ```public int``` hashCode() 

## The equals method on Object
- The implementation of equals on Object is shown here.
- This simply returns this == obj.
    ```
    public boolean equals (Object obj) {
            return (this == obj);
    }
    ```
- Do you remember what == means for object ?
    - It means two variables have the same reference to a single object in memory.
    - Because both references are pointing to the same object, then this is obviously a good equality test. 
- Equality of Objects: 
    - Objects can be considered equal in other instances as well, if their attribute values are equal, for example. 
    - The String class overrides this method, so that it compares all the characters in each String, to confirm that two Strings are equal. 
- Example: 
    - Print each of these Strings out, along with their hashCodes.
    ```
    Input
        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l","He","lo");
        String dText = "He".concat("llo");
        String eText = "hello";
        List<String> hellos = Arrays.asList(aText, bText, cText, dText, eText);

    Output
    Hello: 69609650
    Hello: 69609650
    Hello: 69609650
    Hello: 69609650
    hello: 99162322
    ```
    - Java doesn't care if these are different objects in memory, when it tests the equality of Strings, using the equal method. 
    - It just cares that the characters match, in one instance, compared to another instance. 

## HashSet
- A hash set is a class that implements the Set interface, and tracks duplicates by their hash code. 
- Most collections allow the creation of another collection type, by passing a different collection to the constructor. 
    - Such as passing a List to a Set, but a Set's constructor allows any instance that implements Collection to be passed to it. 
    ```
    Set<String> mySet = new HashSet<>(hellos);
    System.out.println("mySet = " + mySet);
    System.out.println("# of elements = " + mySet.size());  

    Output:
    mySet = [Hello, hello]
    # of elements = 2
    Hello: 0, 1  
    hello: 4
    ```
    - The hash set will only add new references to its collection if it doesn't find a match in its collections, first using the hashCode and then the object's equals method. 
    - It uses the hashCode to create a bucket identifier to store the new reference.

## The visual representation of the code
![IMG_9244](https://github.com/huygi/Java_Collections/assets/105019803/b54bfc9c-bdcc-471e-a4aa-c687b373e08e)
- Our code set up five String reference variables, but two of these, referenced the same string object in memory, as shown here with aText and bText pointing to the same string instance. 
- When we passed our list of five strings to the HashSet, it added only unique instances to its collection. 
- It locates elements to match, by first deriving which bucket to look through, based on the hash code. 
- It then compares those elements to the next element to be added, with other elements in that bucket, using the equals method. 
- Need to understand how equality, and the hashCode go hand in hand, when using hashed collections. 
 
## Coding example
- Create a set of cards and add the cards one at a time: 
  ```
  Input:
  PlayingCard aceHearts = new PlayingCard( "Heart","Ace");
  PlayingCard kingClubs = new PlayingCard( "Clubs", "King");
  PlayingCard queenSpades = new PlayingCard ("Spades", "Queen");
  
  List<PlayingCard> cards = Arrays.asList( aceHearts, kingClubs, queenSpades );
  
  Set<PlayingCard> deck = new HashSet<>();
        for ( PlayingCard c : cards ) {
            if( !deck.add(c) ) {          
                System.out.println( "Found a duplicate for " + c );
            }
        }
        System.out.println(deck);
  
  Output:
  Ace of Heart: 2065951873
  King of Clubs: 793589513
  Queen of Spades: 1313922862
  [Ace of Heart, King of Clubs, Queen of Spades]
  ```
- If all hashCodes are the same (hashCode = 1):
  ```
  Output:
  Ace of Heart: 1
  King of Clubs: 1
  Queen of Spades: 1
  --> Checking Playing Card equality
  Found a duplicate for King of Clubs
  --> Checking Playing Card equality
  Found a duplicate for Queen of Spades
  [Ace of Heart]
  ```
- If hashCode of Ace of Heart = 11 and others = 12:
  ```
  Input:
  this.internalHash = (suit.equals("Heart")) ? 11 : 12;
  
  Output:
  Ace of Heart: 11
  King of Clubs: 12
  Queen of Spades: 12
  --> Checking Playing Card equality
  Found a duplicate for Queen of Spades
  [Ace of Heart, King of Clubs]
  ```
- hashCode() and equals() implementation that was generated by IntelliJ:
  ```
  Input:
  @Override
  public boolean equals(Object o) {
     if (this == o) return true; 
     if (o == null || getClass() != o.getClass()) return false;
     PlayingCard that = (PlayingCard) o;
     if (!suit.equals(that.suit)) return false;  
     return face.equals(that.face);              
  }
  
  @Override
  public int hashCode() {
     int result = suit.hashCode();
     result = 31 * result + face.hashCode(); 
     return result;
  }
  
  Output:
  Ace of Heart: -2137324291
  King of Clubs: 2023815418
  Queen of Spades: -269088580
  [Ace of Heart, King of Clubs, Queen of Spades]
  ```
  
## Creating the hashCode method
- You don't have to use the generated algorithm as I did here. 
- You could create your own, but your code should stick to the following rules. 
  1. It should be very fast to compute.
  2. It should produce a consistent result each time it's called. For example, you wouldn't want to use a random number generator, or a date time based algorithm that would return a different value each time the method is called.
  3. Objects that are considered equal should produce the same hashCode. 
  4. Values used in the calculation should not be mutable.

- It is common practice to include a small prime number as a multiplicative factor (although some non-prime numbers also provide good distributions).
- This helps ensure a more even distribution for the bucket identifier algorithm, especially if your data might exhibit clustering in some way. 
- Intellij and other code generation tools use 31, but other good options could be 29, 33 (not prime but shown to have good results), 37, 43 and so on. 
- You want to avoid the single digit primes, because more numbers will be divisible by those, and may not produce the even distribution that will lend itself to improved performance.
- Remember that if you are using your own classes in hashed collections, you'll want to override both the equals and the hashCode methods. 
- Note:
  - Java supports four hashed collections implementation, which we'll be looking at coming up. 
  - These are the HashSet, LinkedHashSet, the HashMap, and LinkedHashMap.
  - There's one legacy implementation, the HashTable. However, there are more efficient implementations which replace this legacy class. 