import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.List;

import java.util.StringTokenizer;

import java.awt.image.*;
import java.awt.image.renderable.RenderableImageOp;

/** This program reads javafx commands from text files and draws
* images that are saved as png's.
*
* Date last modified: 12/6/2024
* @author Nathan Kenney
* Sources used:
*
* https://www.geeksforgeeks.org/extract-all-integers-from-the-
* given-string-in-java/
*
* https://www.freecodecamp.org/news/string-to-array-in-java-how-to-convert-a-
* string-to-an-array-in-java/
*
* https://www.geeksforgeeks.org/java-program-to-convert-string-to-float-value/
*
* https://docs.oracle.com/javase/tutorial/2d/images/saveimage.html
*
* https://docs.oracle.com/javase/8/javafx/api/javafx/embed/swing
* SwingFXUtils.html
*/

public class GraphicsInterpreter extends AbstractGraphicsInterpreter { 

    // Codes for each command
        private final int SIZE = 100;
        private final int LINE = 110;
        private final int CIRCLE = 120;
        private final int RECTANGLE = 130;
        private final int POLYGON = 140;
        private final int TEXT = 150;
        private final int FILL = 160;
        private final int STROKE = 170;
        private final int COMMENT = 180;
        private final int END = 190;
        private final int EMPTY = 200;

    // Default color values.
    private Color fillColor = Color.WHITE;
    private Color strokeColor = Color.BLACK;

    public String lookForNumbers ( String line ) {

        // This line replaces every char in a string 
        // thats not a number between 0-9.
        line = line.replaceAll( "[^0-9.\\-]", " " );

        // Next two lines make all white spaces only a single space long.
        line = line.replaceAll( " +", " " );
        
        line = line.trim();

        return line;
    }

  public void convertNumberStringIntoArray ( String line, float[] numbers ) {
      String newLine = lookForNumbers( line );
      // Creating a StringTokenizer object with delimiter " "
      StringTokenizer tokenizer = new StringTokenizer( newLine, " " );
      int tokenCount = tokenizer.countTokens();
      try {        
          int i = 0;
          while ( tokenizer.hasMoreTokens() && i < numbers.length ) {
              String token = tokenizer.nextToken().trim();
              //System.out.println( "Current Number: " + token );      
              numbers [ i ] = Math.round( Float.parseFloat( token ) );
              i++;
          }
      }

      catch ( NumberFormatException e ) {
          throw e;
      }
    }
    // Returnable string method that looks for commands and
    // interprets them as codes.
    public int lookForCommand ( String line ) {
        line = line.trim();

        if ( line.isEmpty() ) return EMPTY;
        if ( line.contains( "SIZE" ) ) return SIZE;
        if ( line.contains( "LINE" ) ) return LINE;
        if ( line.contains( "CIRCLE" ) ) return CIRCLE;
        if ( line.contains( "RECTANGLE" ) ) return RECTANGLE;
        if ( line.contains( "POLYGON" ) ) return POLYGON;
        if ( line.contains( "TEXT" ) ) return TEXT;
        if ( line.contains( "FILL" ) ) return FILL;
        if ( line.contains( "STROKE" ) ) return STROKE;
        if ( line.contains( "//" ) ) return COMMENT;
        if ( line.contains( "END" ) ) return END;

        return EMPTY;
    }
    
    @Override 
    public WritableImage loadCommandFile( Stage stage, String filename )
            throws FileNotFoundException, Exception {

        int imageWidth = (int) stage.getWidth();
        int imageHeight = (int) stage.getHeight();

        WritableImage writableImage = new WritableImage(imageWidth, 
                imageHeight);

        File file = new File( filename );
        Pane root = new Pane( );
        Scene scene = new Scene( root, stage.getWidth(), stage.getHeight() );

        try (Scanner input = new Scanner( file ); ) {

            float[] propertyNumbers = new float[ 5 ];
            int code = 0;
            String line = null;          

            while ( code != END && input.hasNextLine() ) {
                line = input.nextLine();
                System.out.println("Line read: " + line);
                code = lookForCommand( line );
                System.out.println("Command code: " + code);
                convertNumberStringIntoArray( line, propertyNumbers );

                switch ( code ) {
                    case EMPTY:
                        continue;
                    case SIZE:

                        imageWidth = (int) propertyNumbers[0];
                        imageHeight = (int) propertyNumbers[1];
                        writableImage = new WritableImage(imageWidth, 
                                imageHeight);
                        break;
                    case LINE:
                         Line drawingLine = new Line( propertyNumbers[ 0 ],
                                propertyNumbers[ 1 ], propertyNumbers[ 2 ],
                                propertyNumbers[ 3 ] );
                        drawingLine.setStroke( strokeColor );
                         root.getChildren().add( drawingLine );
                        break;
                    case CIRCLE:            
                        Circle circle = new Circle( propertyNumbers[ 0 ],
                                propertyNumbers[ 1 ], propertyNumbers[ 2 ] );
                        circle.setFill( fillColor );
                        circle.setStroke( strokeColor );
                        root.getChildren().add( circle );
                        break;
                    case RECTANGLE:
                        Rectangle rectangle = new Rectangle(   
                                propertyNumbers[ 0 ], propertyNumbers[ 1 ], 
                                propertyNumbers[ 2 ], propertyNumbers[ 3 ] );
                        rectangle.setFill( fillColor );
                        rectangle.setStroke( strokeColor );
                        root.getChildren().add( rectangle );
                        break;
                    case POLYGON:
                        Polygon polygon = new Polygon( propertyNumbers[ 0 ], 
                                propertyNumbers[ 1 ], propertyNumbers[ 2 ], 
                                propertyNumbers[ 3 ], propertyNumbers[ 4 ],
                                propertyNumbers[ 5 ] );
                        polygon.setFill( fillColor );
                        polygon.setStroke( strokeColor );
                        root.getChildren().add( polygon );
                        break;
                    case TEXT:
                        // Removes the keyword and property numbers from the
                        // string before it is displayed.
                        String propertyOne = String.format( "%.0f",
                                propertyNumbers[ 0 ] );
                        String propertyTwo = String.format( "%.0f", 
                                propertyNumbers[ 1 ] );
                        line = line.replace( "TEXT", "" ).replace( 
                                propertyOne, "" ).replace(  
                                    propertyTwo, "" );

                        Text text = new Text( propertyNumbers[ 0 ],
                                propertyNumbers[ 1 ], line );
                        text.setFill( strokeColor );
                        root.getChildren().add( text );
                        break;
                    case FILL:
                        fillColor = Color.rgb(
                                ( int ) propertyNumbers[ 0 ],
                                ( int ) propertyNumbers[ 1 ],
                                ( int ) propertyNumbers[ 2 ],
                                propertyNumbers[ 3 ] );
                        break;
                    case STROKE:
                        strokeColor = Color.rgb(
                                ( int ) propertyNumbers[ 0 ],
                                ( int ) propertyNumbers[ 1 ],
                                ( int ) propertyNumbers[ 2 ],
                                propertyNumbers[ 3 ] );
                        break;
                    case COMMENT:
                        System.out.println( line );
                        continue;
                    case END:
                        System.out.println( "Reached end of code" );
                        break;
                    default:
                        continue;
                }            
            }        

        } catch ( Exception e ) {
            e.printStackTrace();
        }
  
        // Render the scene to the WritableImage
        scene.snapshot(writableImage);
        return writableImage;
    }
    @Override
    public void saveImageFile( WritableImage image, String filename ) throws 
            FileNotFoundException, IOException { 
        try {
            // retrieve image
            // BufferedImage bufferedImage = new BufferedImage();
            File outputfile = new File( filename );
            ImageIO.write( 
                    javafx.embed.swing.SwingFXUtils.fromFXImage( image, 
                    null ), "png", outputfile );
        } catch ( IOException e ) {

        }
    }

    @Override
    public void start( Stage stage ) {

        int width = 400;
        int height = 300;

        Pane root = new Pane( );
        Scene scene = new Scene( root, width, height );

        stage.setTitle( "My JavaFX Graphics Command File Interpreter" );
        stage.setScene( scene );
        stage.show();

        String loadFilename = getParameter( 0 );
        String saveFilename = getParameter( 1 );
        System.out.println(loadFilename + " " + saveFilename);
        try {
            WritableImage image = loadCommandFile( stage, loadFilename );
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
