import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

//Controller with all the methods to initialize the mitrex and to fill the squares
public class myControllerClass {

    @FXML
    private Canvas canvas;

    @FXML
    private ToolBar tool;

    @FXML
    private VBox vbox;

    @FXML
    public void initialize() {
        drawMatrix();  // Automatically draw the matrix when the controller is initialized
    }

    @FXML
    void actionStarted(MouseEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int cellSize = 10;
        int cols = (int) (canvas.getWidth() / cellSize);
        int rows = (int) (canvas.getHeight() / cellSize);
        int totalCells = cols * rows;
        int fillCount = (int) (totalCells * 0.1); // 10% of squares

        // Clear the canvas before redrawing
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //Setting the lines again
        drawMatrix();

        gc.setFill(Color.PURPLE); // Set fill color
        Random rand = new Random();
        Set<String> filledCells = new HashSet<>();

        while (filledCells.size() < fillCount) {
            int randomCol = rand.nextInt(cols);
            int randomRow = rand.nextInt(rows);
            String key = randomCol + "," + randomRow;

            if (!filledCells.contains(key)) { // Avoid filling the same square twice
                filledCells.add(key);
                gc.fillRect(randomCol * cellSize, randomRow * cellSize, cellSize, cellSize);
            }
        }
    }

    private void drawMatrix() {
        GraphicsContext gc = canvas.getGraphicsContext2D();  // Get the GraphicsContext

        gc.setStroke(Color.BLACK);  // Set the color of the lines
        gc.setLineWidth(1);  // Set the line width

        int canvasWidth = (int) canvas.getWidth();  // Get canvas width
        int canvasHeight = (int) canvas.getHeight();  // Get canvas height

        // Draw vertical lines with 10px spacing
        for (int x = 0; x <= canvasWidth; x += 10) {
            gc.strokeLine(x, 0, x, canvasHeight);  // Vertical lines from top to bottom
        }

        // Draw horizontal lines with 10px spacing
        for (int y = 0; y <= canvasHeight; y += 10) {
            gc.strokeLine(0, y, canvasWidth, y);  // Horizontal lines from left to right
        }
    }
}
