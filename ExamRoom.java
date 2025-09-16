import java.util.Random;
import java.util.Scanner;
/**
 * This class is an examination room in Game of Zuul. The user must correctly answer the question asked in order to
 * proceed in the game

 * @author Charis Nobossi, 101297742
 * @version February 15th, 2025
 */
public class ExamRoom extends Room{
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    int random1;
    int random2;
    private Random rand = new Random();

    public ExamRoom(String description) {
        super(description);
    }

    public boolean getEquation(){
        random1 = rand.nextInt(25);
        random2 = rand.nextInt(25);
        System.out.println("Welcome to the exam hall! Don't stress - we promise to go easy on you ;)");
        System.out.println("Let's see if you're smarter than a 3 grader.... answer this equation correctly or else....");
        System.out.println("PS: No calculators allowed :)");
        System.out.println("QUICK..... what is " + random1 + " + " + random2 + "?");

        Scanner scanner = new Scanner(System.in); //  Create a Scanner for input
        int userAnswer = scanner.nextInt(); //  Get user's input

        if (userAnswer == (random1 + random2)) {
            System.out.println("Correct! You may proceed.");
            return true;
        }

        else {
            System.out.println("Wrong! The correct answer was " + (random1 + random2) + ".");
            System.out.println("............Unfortunately, after being pushed off the top of Dunton Tower, you have tumbled to a painful, untimely death.");
            System.out.println("     x_x     ");
            return false;
        }
    }
}


