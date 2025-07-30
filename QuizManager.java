package question;
import java.util.*;
import java.util.concurrent.*;

public class QuizManager {
    private List<Question> questions;
    private int score;
    private Scanner scanner;

    public QuizManager() {
        questions = new ArrayList<>();
        scanner = new Scanner(System.in);
        score = 0;

        questions.add(new Question("Java is a ___ language?", new String[]{"Interpreted", "Compiled", "Both", "None"}, 2));
        questions.add(new Question("Which keyword is used to inherit a class?", new String[]{"implement", "extends", "inherits", "super"}, 1));
        questions.add(new Question("Which method is the entry point in Java?", new String[]{"start()", "main()", "run()", "init()"}, 1));
    }

    public void startQuiz() {
        System.out.println("📚 Starting Quiz... You have 10 seconds per question!");
        for (Question q : questions) {
            askQuestionWithTimer(q, 20);
        }
        System.out.println("✅ Quiz Finished! Your Score: " + score + "/" + questions.size());
    }

    private void askQuestionWithTimer(Question q, int seconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> input = executor.submit(() -> {
            System.out.println("\n" + q.getQuestionText());
            String[] opts = q.getOptions();
            for (int i = 0; i < opts.length; i++) {
                System.out.println((i + 1) + ". " + opts[i]);
            }
            System.out.print("Your answer (1-" + opts.length + "): ");
            return scanner.nextInt() - 1;
        });

        try {
            int answer = input.get(seconds, TimeUnit.SECONDS);
            if (q.isCorrect(answer)) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Incorrect.");
            }
        } catch (TimeoutException e) {
            System.out.println("⏰ Time’s up!");
            input.cancel(true);
        } catch (Exception e) {
            System.out.println("⚠️ Invalid input.");
            input.cancel(true);
        } finally {
            executor.shutdownNow();
        }
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
