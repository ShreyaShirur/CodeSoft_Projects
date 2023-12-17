import java.util.*;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : questions) {
            displayQuestion(question);
            System.out.print("Your answer (1-" + question.getOptions().size() + "): ");

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    timer.cancel();
                }
            };

            timer.schedule(task, 15000); // 15 seconds timer for each question
            int userAnswer = scanner.nextInt();
            timer.cancel();

            if (userAnswer > 0 && userAnswer <= question.getOptions().size()) {
                checkAnswer(question, userAnswer);
            } else {
                System.out.println("Invalid answer. Skipping to the next question.");
            }
        }

        displayResult();
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println("\n" + question.getQuestion());

        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private void checkAnswer(QuizQuestion question, int userAnswer) {
        if (userAnswer - 1 == question.getCorrectOptionIndex()) {
            System.out.println("Correct!\n");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was: " +
                    (question.getCorrectOptionIndex() + 1) + ". " +
                    question.getOptions().get(question.getCorrectOptionIndex()) + "\n");
        }
    }

    private void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        // Sample quiz questions
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the capital of France?",
                Arrays.asList("Berlin", "Paris", "London", "Madrid"), 1));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                Arrays.asList("Venus", "Mars", "Jupiter", "Saturn"), 1));
        questions.add(new QuizQuestion("What is the largest mammal?",
                Arrays.asList("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"), 1));

        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
