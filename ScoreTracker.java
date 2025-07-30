package question;
import java.io.*;
import java.time.LocalDateTime;

public class ScoreTracker {
    private static final String FILE_PATH = "data/scores.txt";

    public void recordScore(String username, int score, int total) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(LocalDateTime.now() + " - " + username + ": " + score + "/" + total);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("⚠️ Error saving score history.");
        }
    }

    public void showHistory() {
        System.out.println("\n📖 Score History:");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            System.out.println("⚠️ No score history available.");
        }
    }
}
