import java.util.*;

/**
 * DISCLAIMER: I have used Professor Marshall's solution posted of A1 to complete A2, instead of fixing my errors in A1.
 *
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text-based adventure game. Users
 * can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * DISCLAIMER: I have used professor Marshall's solution posted of A1 to complete A2, instead of fixing my errors in A1.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 *
 * @author Lynn Marshall
 * @version October 12th, 2012
 *
 * @author Charis Nobossi, 101297742
 * @version February 10th, 2025
 */
import java.util.Stack;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> previousRoomStack;
    private Item basket = null;
    private int hunger = -1;
    private boolean charged;
    private ArrayList <Room> ArrayRooms;
        
    /**
     * Create the game and initialise its internal map, as well
     * as the previous room (none) and previous room stack (empty).
     */
    public Game()
    {
        ArrayRooms = new ArrayList<Room>();
        createRooms();
        parser = new Parser();
        previousRoom = null;
        previousRoomStack = new Stack<Room>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
        TransporterRoom transporterchamber;
        ExamRoom examroom;
        Item chair1, chair2, chair3, chair4, bar, computer1, computer2, computer3, tree1, tree2, cookie1, cookie2, cookie3, beamer1, beamer2;
        
        // create some items
        chair1 = new Item("chair", "a wooden chair",5);
        chair2 = new Item("chair", "a wooden chair",5);
        chair3 = new Item("chair", "a wooden chair",5);
        chair4 = new Item("chair", "a wooden chair",5);
        bar = new Item("bar", "a long bar with stools",95.67);
        computer1 = new Item("computer", "a PC",10);
        computer2 = new Item("computer", "a Mac",5);
        computer3 = new Item("computer", "a PC",10);
        tree1 = new Item("tree", "a fir tree",500.5);
        tree2 = new Item("tree", "a fir tree",500.5);
        cookie1 = new Item("cookie", "a chocolate chip cookie", 0.03);
        cookie2 = new Item("cookie", "a sugar cookie", 0.03);
        cookie3 = new Item("cookie", "a maple cookie", 0.03);
        beamer1 = new Beamer("beamer", "a shiny beamer", 0.2);
        beamer2 = new Beamer("beamer", "a shiny beamer", 0.2);



        // create the rooms
        outside = new Room("outside the main entrance of the university");
        ArrayRooms.add(outside);
        theatre = new Room("in a lecture theatre");
        ArrayRooms.add(theatre);
        pub = new Room("in the campus pub");
        ArrayRooms.add(pub);
        lab = new Room("in a computing lab");
        ArrayRooms.add(lab);
        office = new Room("in the computing admin office");
        ArrayRooms.add(office);
        transporterchamber = new TransporterRoom("In the transporter chamber", ArrayRooms);
        examroom = new ExamRoom("in the exam hall");
        ArrayRooms.add(examroom);
        QuizRoom hallOfNumbers = new QuizRoom("in the Hall of Numbers", new MathQuiz(2, 12));

        QuizRoom atlasChamber = new QuizRoom("in the Atlas Chamber", new StaticQuiz("What is the capital of France?", "Paris", "It’s also called the City of Light."));

        QuizRoom riddleStudy = new QuizRoom("inside the Riddle Study", new StaticQuiz("I speak without a mouth and hear without ears. What am I?", "Echo", "It repeats after you."));



        // put items in the rooms
        outside.addItem(tree1);
        outside.addItem(tree2);
        theatre.addItem(chair1);
        theatre.addItem(cookie1);
        theatre.addItem(beamer2);
        pub.addItem(bar);
        pub.addItem(cookie2);
        lab.addItem(chair2);
        lab.addItem(computer1);
        lab.addItem(chair3);
        lab.addItem(computer2);
        lab.addItem(beamer1);
        office.addItem(chair4);
        office.addItem(computer3);
        office.addItem(cookie3);
        
        // initialise room exits
        outside.setExit("east", theatre); 
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);
        theatre.setExit("north", transporterchamber);

        pub.setExit("east", outside);
        pub.setExit("north", examroom);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        examroom.setExit("south", pub);

        transporterchamber.setExit("...surprise...", theatre);

        outside.setExit("east", hallOfNumbers);

        hallOfNumbers.setExit("west", outside);
        hallOfNumbers.setExit("north", atlasChamber);

        atlasChamber.setExit("south", hallOfNumbers);
        atlasChamber.setExit("east", riddleStudy);

        riddleStudy.setExit("west", atlasChamber);

        ArrayRooms.add(hallOfNumbers);
        ArrayRooms.add(atlasChamber);
        ArrayRooms.add(riddleStudy);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed
     * @return true If the command ends the game, false otherwise
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        else if (commandWord.equals("stackBack")) {
            stackBack(command);
        }
        else if (commandWord.equals("take")) {
            take(command);
            }
        else if (commandWord.equals("drop")){
            drop(command);
        }
        else if (commandWord.equals("charge")){
            charge(command);
        }
        else if (commandWord.equals("fire")){
            fire(command);
        }
        else if(commandWord.equals("answer")) {
            handleAnswer(command);
            return false; // or whatever your loop expects
        }


        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print a cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * If we go to a new room, update previous room and previous room stack.
     * 
     * @param command The command to be processed
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        // If there's a quiz blocking the path in the current room, don't allow movement yet.
        if (currentRoom instanceof QuizRoom) {
            QuizRoom qr = (QuizRoom) currentRoom;
            if (!qr.isSolved()) {
                System.out.println("A challenge blocks your path. Use: answer <your answer>");
                System.out.println(qr.getPrompt());
                return;
            }
        }

        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction); // may be random in TransporterRoom

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return;
        }

        // If the next room is an ExamRoom, ask the equation before moving in.
        if (nextRoom instanceof ExamRoom) {
            ExamRoom examRoom = (ExamRoom) nextRoom;
            boolean passed = examRoom.getEquation(); // ask the equation

            if (!passed) {
                System.out.println("Game Over :(");
                System.exit(0); // end the game if the answer is wrong
                return; // safety
            }
        }

        // Move to the next room
        previousRoom = currentRoom;
        previousRoomStack.push(currentRoom);
        currentRoom = nextRoom;

        // Show the new room description
        System.out.println(currentRoom.getLongDescription());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     * @return true, if this command quits the game, false otherwise
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /** 
     * "Look" was entered. Check the rest of the command to see
     * whether we really want to look.
     * 
     * @param command The command to be processed
     */
    private void look(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Look what?");
        }
        else {
            // output the long description of this room, including whether you are carrying anything
            if(basket == null){
                System.out.println(currentRoom.getLongDescription());
                System.out.println("You are not carrying anything");
            }
            else{
                System.out.println(currentRoom.getLongDescription());
                System.out.println("You are carrying a " + basket.getName());

            }
        }
    }
    
    /** 
     * "Eat" was entered. Check the rest of the command to see
     * whether we really want to eat.
     * 
     * @param command The command to be processed
     */
    private void eat(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Eat what?");

        } else {
            if (basket == null) {
                System.out.println("You have no food");
            } else {
                // output that we have eaten

                String name = basket.getName();

                if(name.equals("cookie")){
                    System.out.println("You have eaten and are no longer hungry.");
                    basket = null;
                    hunger = 0;
                }
                else{
                    System.out.println("You have no food");
                }

            }
        }

    }
    private void handleAnswer(Command command) {
        if (!(currentRoom instanceof QuizRoom)) {
            System.out.println("There's no challenge to answer here.");
            return;
        }
        if (!command.hasSecondWord()) {
            System.out.println("Answer what? Usage: answer <your answer>");
            return;
        }
        QuizRoom qr = (QuizRoom) currentRoom;
        String playerAnswer = command.getSecondWord();

        if (qr.attempt(playerAnswer)) {
            System.out.println("✅ Correct! The way forward is open.");
            System.out.println(currentRoom.getLongDescription());
        } else {
            System.out.println("❌ Not quite. Try again!");
            String hint = new MathQuiz(1,1).hint(); // dummy to access default
            // better: use qr.getPrompt() or quiz.hint()
            System.out.println(qr.getPrompt());
        }
    }

    /** 
     * "Back" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     */
    private void back(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Back what?");
        }
        else {
            // go back to the previous room, if possible
            if (previousRoom==null) {
                System.out.println("No room to go back to.");
            } else {
                // go back and swap previous and current rooms,
                // and put current room on previous room stack
                Room temp = currentRoom;
                currentRoom = previousRoom;
                previousRoom = temp;
                previousRoomStack.push(temp);
                // and print description
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }
    
    /** 
     * "StackBack" was entered. Check the rest of the command to see
     * whether we really want to stackBack.
     * 
     * @param command The command to be processed
     */
    private void stackBack(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("StackBack what?");
        }
        else {
            // step back one room in our stack of rooms history, if possible
            if (previousRoomStack.isEmpty()) {
                System.out.println("No room to go stack back to.");
            } else {
                // current room becomes previous room, and
                // current room is taken from the top of the stack
                previousRoom = currentRoom;
                currentRoom = previousRoomStack.pop();
                // and print description
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }
    /**
     * "take" was entered. Check the rest of the command to see
     * whether we really want to take an item.
     *
     * @param command The command to be processed
     */
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
        }

        else{
            if(basket == null){
                String name = command.getSecondWord();
                if(hunger != -1 && hunger < 5) {
                    for (Item item : currentRoom.getItemsArray()) {
                        if (name.equals(item.getName())) {
                            basket = item;
                            System.out.println("You have picked up a " + item.getName());
                            currentRoom.getItemsArray().remove(item);
                            hunger += 1;
                            return;
                        }

                    }
                    System.out.println("You cannot pick this item up because it is not in this room");
                }
                else{
                    if(name.equals("cookie")){
                        for (Item item : currentRoom.getItemsArray()) {
                            if (name.equals(item.getName())) {
                                basket = item;
                                System.out.println("You have picked up a " + item.getName());
                                currentRoom.getItemsArray().remove(item);
                                hunger += 1;
                                return;
                            }

                        }
                        System.out.println("You cannot pick this item up because it is not in this room");
                    }
                    else{
                        System.out.println("You are hungry!! You must find a cookie and eat it.");
                    }
                }
            }
            else{
                System.out.println("You must drop the item you are holding before you pick this one up");

            }
        }
    }
    /**
     * "drop" was entered. Check the rest of the command to see
     * whether we really want to drop.
     *
     * @param command The command to be processed
     */

    private void drop(Command command) {
        if(basket == null){
            System.out.println("You are not holding anything");
        }
        else{
            currentRoom.getItemsArray().add(basket);
            System.out.println("You have dropped the " + basket.getName() + " you were holding");
            basket = null;
        }
    }
    /**
     * "charge" was entered. Check the rest of the command to see
     * whether we really want to charge the beamer.
     *
     * @param command The command to be processed
     */

    private void charge(Command command){
        if(basket.getName().equals("beamer")) {
            if (!charged) {
                Beamer bbasket = (Beamer) basket;
                bbasket.setDestination(currentRoom);
                charged = true;
                System.out.println("The beamer has been successfully charged");
            }
            else{
                System.out.println("You must fire the beamer before you charge it again");
            }
        }
        else{
            System.out.println("You are not carrying a beamer. Pick one up before being able to charge it! ");
        }
    }
    /**
     * "fire" was entered. Check the rest of the command to see
     * whether we really want to fire the beamer.
     *
     * @param command The command to be processed
     */

    private void fire(Command command) {
        if (basket.getName().equals("beamer")) {
            if(charged) {
                Beamer bbasket = (Beamer) basket;
                previousRoomStack.push(currentRoom); // add to previous room stack
                currentRoom = bbasket.getDestination();
                System.out.println("...........teleporting............");
                System.out.println("You have arrived " + currentRoom.getShortDescription() + "!!");
                charged = false;
            }
            else{
                System.out.println("You must charge the beamer before you can fire it");

            }
        }
        else{
            System.out.println("You are not carrying a beamer. Pick one up before being able to charge it! ");
        }
    }

}
