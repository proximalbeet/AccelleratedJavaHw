import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class JavaFxDemo extends Application {
    public void start( Stage Stage ) {
        Group root = new Group();


        Rectangle r = new Rectangle( 25, 25, 250, 250 );

        r.setFill( Color.BLUE );

        root.getChildren().add( r );


        Scene scene = new Scene( root, 800, 600 );

        scene.setRoot( root );

        stage.setScene( scene );

        stage.setTitle( "Welcome to javafx!" );

        stage.sizeToScene();

        stage.show();
    }
}
