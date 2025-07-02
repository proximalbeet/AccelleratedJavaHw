import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class AbstractGraphicsInterpreter extends Application {
    public abstract WritableImage loadCommandFile(Stage stage, String filename) throws FileNotFoundException, Exception;
    public abstract void saveImageFile(WritableImage image, String filename) throws FileNotFoundException, IOException;

    /**
     * The start method. Required by Application
     * @param stage
     */
    public void start(Stage stage) {
        Pane root = new Pane( );
        Scene scene = new Scene( root, 400, 300 );

        stage.setTitle("My JavaFX Graphics Command File Interpreter");
        stage.setScene(scene);
        stage.show();

        String loadFilename = getParameter( 0 );
        String saveFilename = getParameter( 1 );
        try {
            WritableImage image = loadCommandFile(stage, loadFilename);
            saveImageFile( image, saveFilename );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private String getParameter( int index ) {
        Parameters params = getParameters();
        List<String> parameters = params.getRaw();
        return !parameters.isEmpty() ? parameters.get(index) : "";
    }

}
