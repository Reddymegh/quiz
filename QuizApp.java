package question;
import java.util.Scanner;

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("👤 Enter your name: ");
        String username = scanner.nextLine();

        QuizManager manager = new QuizManager();
        manager.startQuiz();

        ScoreTracker tracker = new ScoreTracker();
        tracker.recordScore(username, manager.getScore(), manager.getTotalQuestions());
        tracker.showHistory();
    }
}
