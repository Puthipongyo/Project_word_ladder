package Project_word_ladder;
import java.util.*;

public class main_Project2 {
    public static void main(String[] args) {
        System.out.println("Enter word file = ");
        String filename;
        Scanner scanner = new Scanner(System.in);
        filename = scanner.nextLine();
        word_ladder wl  = new word_ladder(filename);
        wl.menu();
    }
}
