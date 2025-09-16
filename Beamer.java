/**
 * This class represents a beamer, which is a subclass of item and which may be put
 * in a room in the game of Zuul. It can also be fired and charged, to transport the player to another room
 * Disclaimer** I am using the posted solution for A1 to do A2 , instead of my own solution that contained errors.
 * @author Lynn Marshall
 * @version A1 Solution
 *
 * @author Charis Nobossi, 101297742
 * @version February 15th, 2025
 */
public class Beamer extends Item{



    private Room Destination;
    /**
     * Constructor for objects of class Beamer.
     *
     * @param name The name of the beamer.
     * @param description The description of the beamer.
     * @param weight The weight of the beamer.
     */
    public Beamer(String name, String description, double weight) {
        super(name, description, weight);
    }

    /**
     * Sets the destination room for the beamer.
     *
     * @param currentRoom The room to set as the beamer's destination.
     */
    public void setDestination(Room currentRoom){
        this.Destination = currentRoom;
    }

    /**
     * Gets the destination room where the beamer is set to transport.
     *
     * @return The destination room of the beamer.
     */
    public Room getDestination(){
        return this.Destination;
    }

}
