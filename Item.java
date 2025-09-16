
/**
 * This class represents an item which may be put
 * in a room in the game of Zuul.
 * @author Lynn Marshall
 * @version A1 Solution
 *
 * @author Charis Nobossi, 101297742
 * @version February 15th, 2025
 */
public class Item
{
    // description of the item
    private String description;
    
    // weight of the item in kilograms
    private double weight;

    // name of the item

    private String name;


    /**
     * Constructor for objects of class Item.
     * 
     * @param description The description of the item
     * @param weight The weight of the item
     */
    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    /**
     * Returns a description of the item, including its
     * description and weight.
     * 
     * @return  A description of the item
     */
    public String getDescription()
    {
        return description + " that weighs " + weight + "kg.";
    }

    /**
     * Returns the items name
     * @return items name
     */
    public String getName(){
        return this.name;
    }


}
