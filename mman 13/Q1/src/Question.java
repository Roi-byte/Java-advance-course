import java.util.ArrayList;
import java.util.Collections;

// a function to represents a question in the quiz.
public class Question {

    private String question;
    private ArrayList<String> possibleAnswers;
    private String correctAnswer;

    public Question(String question, ArrayList<String> answers) {
        this.question = question;
        this.correctAnswer = answers.get(0); // The first answer is always the correct one
        this.possibleAnswers = answers;
    }

    // Getter for question
    public String getQuestion() {
        return question;
    }

    // Return a list of possible answers
    public ArrayList<String> getAnswers() {
        Collections.shuffle(possibleAnswers); // Randomize the order of the questions
        return possibleAnswers;
    }

    // Getter for the correct answer
    public String getCorrectAnswer() {
        return correctAnswer;
    }

}
