import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.ArrayList;

// The controller class for the quiz.
public class QuizController {

    @FXML private Text question;
    @FXML private Text answerOne;
    @FXML private Text answerTwo;
    @FXML private Text answerThree;
    @FXML private Text answerFour;
    @FXML private Button btnOne;
    @FXML private Button btnThree;
    @FXML private Button btnTwo;
    @FXML private Button btnFour;
    @FXML private Text result;
    @FXML private Button next;
    private Text[] answersText; // An array of 4 text elements that contain the answers
    private QuizSimulation quiz;

    public QuizController() {
        quiz = new QuizSimulation("exam");
    }

    // Initializing the quiz
    @FXML
    private void initialize() {
        answersText = new Text[] { answerOne, answerTwo, answerThree, answerFour };
        if (quiz.hasQuestions()) {
            result.setText("");
            loadQuestion();
        } else {
            disableAnsBtns();
            next.setDisable(true);
            question.setFill(Color.RED);
            question.setText("Error: the questions file wasn't found or did not contain any questions");
        }
    }

    // Restart the quiz
    private void restartQuiz() {
        quiz.restart();
        enableAnsBtns();
        next.setText("Next Question");
        result.setText("");
        loadQuestion();
    }

    // Gets the user answer
    @FXML
    private void handleAnswerClick(Event event) {
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        switch (id) {
            case "btnOne":
                submitAnswer(answerOne.getText());
                break;
            case "btnTwo":
                submitAnswer(answerTwo.getText());
                break;
            case "btnThree":
                submitAnswer(answerThree.getText());
                break;
            case "btnFour":
                submitAnswer(answerFour.getText());
                break;
            default:
                break;
        }
        disableAnsBtns();
    }

    // Methods to loads the next question or the result if there are no more questions
    @FXML
    private void handleNext() {
        quiz.nextQuestion();
        if (next.getText().equals("End Quiz")) {
            result.setFill(Color.BLACK);
            if (quiz.getScore() == 100){
                result.setText("Your result is: " + String.format("%.1f", quiz.getScore()) + " good job! :)");
            }
            else {
                result.setText("Your result is: " + String.format("%.1f", quiz.getScore()));
            }
            next.setText("Start a new quiz :)");
        } else if (next.getText().equals("Start a new quiz :)")) {
            restartQuiz();
        } else {
            if (quiz.isLastQuestion()) {
                next.setText("End Quiz");
            }
            loadQuestion();
            enableAnsBtns();
            result.setText("");
        }
    }

    // Loads the current question to the screen
    private void loadQuestion() {
        question.setText("Question: " + quiz.getQuestion());
        ArrayList<String> answers = quiz.getPossibleAnswers();
        for (int i = 0; i < answersText.length; i++) {
            answersText[i].setText(answers.get(i));
        }
    }

    // Gets an answer and prints to the user if the answer was correct or not.
    private void submitAnswer(String answer) {
        if (quiz.submitAnswer(answer)) {
            result.setFill(Color.GREEN);
            result.setText("Correct! :)");
        } else {
            result.setFill(Color.RED);
            result.setText("Wrong :(");
        }
    }

    // Enables the answer buttons
    private void disableAnsBtns() {
        btnOne.setDisable(true);
        btnTwo.setDisable(true);
        btnThree.setDisable(true);
        btnFour.setDisable(true);
    }

    // Disables the answer buttons
    private void enableAnsBtns() {
        btnOne.setDisable(false);
        btnTwo.setDisable(false);
        btnThree.setDisable(false);
        btnFour.setDisable(false);
    }
}