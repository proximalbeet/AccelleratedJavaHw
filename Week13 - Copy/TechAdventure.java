import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class TechAdventure {

    public Location[][] world;
    public int xPos = 1;
    public int yPos = 0;
    public Location blank = new Location ( "blank", "");
    public Player player;
    private boolean hasComputer = false;
    private boolean won = false;
    public void Init( ){
        player = new Player( );

        Location field = new Location( "field", "A lush field filled with " +
                "alien grass.");
        field.items.add( new Item( "GRASS", 1 ) );

        Location field2 = new Location( "field",
                "Another field with dense grass." );

        field2.items.add( new Item( "GRASS", 1 ) );

        Location forest = new Location( "forest", "A dense forest. The " +
                "branches are thick and block out most of the sun's light. " +
                "The air is thick with the scent of leaves.");

        Location forest2 = new Location( "forest", "The forest continues " +
            "for a long while, with the branches making it hard to tell " +
            "where exactly it stops." );
        forest2.items.add( new Item( "HATCHET", 1 ) );

        Location radarDish = new Location( "radar dish", "An abandonded " +
            "radar dish once used for communication. There is an old " +
            "computer attatched to the base." );
        radarDish.enemy = new Enemy( "CRAZY SCIENTIST", 1 );
        radarDish.enemy.item = new Item( "CONTROL CHIP", 1 );

        Location swamp = new Location( "swamp", "The air of the swamp is " +
                "heavy and humid. The scent of decay permeates everything " +
                "around you." );

        Location swamp2 = new Location( "swamp", "The ground is covered in " +
                "mud and bugs are rampant." );

        Location alienBog = new Location( "alien bog", "The bog is covered " +
                "in a light blue marsh grass. Red flowers poke up from " +
                "underneath the ground cover." );
        alienBog.items.add( new Item( "FLOWER", 1 ) );
        
        Location junkyard = new Location( "junkyard", "Old metal devices " +
                "pile up around you. I hope you have your tetanus shot." );
        junkyard.items.add( new Item( "WIRES", 1 ) );
        junkyard.enemy = new Enemy( "ROBOT GUNNER", 2 );
        junkyard.enemy.item = new Item( "NAV SYSTEM", 1 );

        Location field3 = new Location( "field", "Another field filled with " +
                "lush alien grass. There are thorny plants on the path." );
        field3.items.add( new Item( "MASSIVE THORN", 1 ) );

        Location spaceport = new Location( "spaceport", "A derelict " +
                "spaceport. There is an old ship still in the hangar. " );
        spaceport.enemy = new Enemy( "AUTOTURRET", 3 );
        spaceport.enemy.item = new Item( "FUEL", 1 );
        world = new Location[][] { 
            {field2, field,  blank, blank, blank},
            {blank, forest, forest2, blank, blank},
            {blank, radarDish, blank, field3, spaceport},
            {blank, swamp, swamp2, alienBog, blank},
            {blank, blank, blank, junkyard, blank}
        };
    }

    
    public static void main ( String[ ] args ) {
        TechAdventure techAdventure = new TechAdventure( );
        techAdventure.Init( );
        int port = args.length == 0 ? 2112 : Integer.valueOf( args[ 0 ] );
        techAdventure.launchServer( port );
    }

    public void launchServer( int port ){
        try{
            HttpServer server = HttpServer.create ( 
                    new InetSocketAddress ( port ), 0 );
            HttpContext context = server.createContext ( "/" );
            context.setHandler ( this::handleRequest );
            server.start( );
            System.out.printf( "Started server on port %s", port );
        } catch( IOException e ) {
            e.printStackTrace( );
        }

    }
    private void handleRequest( HttpExchange httpExchange ) throws IOException{
        //Parse commands into a map
        URI uri = httpExchange.getRequestURI( );
        String room = uri.getPath( ).toUpperCase( ).substring( 1 );
        String query = uri.getQuery( );
        Map< String, String> queryMap = parseQuery( query );
        String command = queryMap.get( "COMMAND" );
        
        //Create response
        String response = "Path: " + room + "/n";
        //System.out.printf( "Request room: %s command: %s\n", room, command );
        switch( room ) {
            case "":
                response = generateLandingPage( );
                break;
               case "ROOM":
                response = processCommand( room, command );
                break;
               default:
                response = mainPage( "Invalid" );
                break;
         }
        // Send the response
        Headers h = httpExchange.getResponseHeaders ( );
        h.set( "Content−Type", "text/plain" );
        httpExchange.sendResponseHeaders( 200, response.length( ) );
        OutputStream os = httpExchange.getResponseBody ( );
        os.write( response.getBytes( ) );
        os.close( );




    }
    private Map<String, String> parseQuery( String query ) {
      HashMap<String,String> map = new HashMap<>( );
      if (query != null) {
         System.out.printf( "query=%s\n", query );
         String[] queryParams = query.split("&");
         for (String param : queryParams) {
            String[] keyValue = param.split("=");
            String key = keyValue[0].toUpperCase( );
            String value = ( keyValue.length > 1 ? 
                    keyValue[1] : "" ).toUpperCase( ).replace('+', ' ');
            map.put( key, value );
         }
      }
      return map;
   }

     private String generateLandingPage( ) {
      String response = "";
      //Set background image
      response += pageSetup( );
      response += "<p>";
      response += "The lush forests of Kubera await you, Prospector.<br/>";
      response += "You are standing in a " + playerLocation( ).name + ".<br/>";
      if( playerLocation( ).items.size( ) != 0 ){
          response += listLocationItems( playerLocation( ) );
      }
      response += createLocationList( );
      response += "</p>";
      response += pageCloser( "" );
      return response;
   }

   /*
    * Creates the main page
    */
   private String mainPage( String contents ){
      String response = "";
      response += pageSetup( );
      response += "<p>";
      response += "You are standing in a " + playerLocation( ).name + ".<br/>";
      if( playerLocation( ).items.size( ) != 0 ){
          response += listLocationItems( playerLocation( ) );
      }
      if( playerLocation( ).enemy != Enemy.blank ){
          response += listLocationEnemys( playerLocation( ) );
      }
      response += createLocationList( );
      response += "</p>";
      response += pageCloser( contents );
      return response;
   }
   private String deathPage( ){
   String response = "";
   response += pageSetup( );
   response += "<p> You have perished! You were not strong enough. Type " +
       "restart to try again.</br></p>";
   response += pageCloser( "" );
   return response;
   }
   private String winPage( ){
       String response = "";
       response += pageSetup( );
       response += "<p> You escaped Kubera! Congrats, prospector! You " +
           "now have a great story to tell...</br></p>";
       response += pageCloser( "Type restart to go again!" );
       return response;
   }
   /*
    * Creates the input box that should be appended to each webpage, and 
    * closes the content div
    */
   private String pageCloser( String contents ){
       String response = "";
       response += "<form action=\"ROOM\">";
       response += "  What do you do? <input id=\"COMMAND\" name=\"COMMAND\" " +
          "type=\"text\"/><br/>";
       response += "  <input id=\"SUBMIT\" name=\"SUBMIT\" value=\"SUBMIT\" " +
          "type=\"submit\">";
       response += "</form>";
       response += "<p>";
       response += contents;
       response += "</p>";
       response += "</div>";
        return response;
   }

    /*
     * Helper function to make getting the world position easier
     */
   private Location playerLocation( ){
       return world[ yPos ][ xPos ];
   }
   /*
    * Creates the page background that should be included with all HTML.
    */
   private String pageSetup( ){
        return "<style> body{ background-image: url('https://i.ibb.co/x37ZB" +
            "Lj/alien-jungle-landscape-b66cmo6axodao4ym.jpg'); background-" +
            "repeat: no-repeat; background-size:150%;} div{background-color" + 
            ":#9999;}</style><div>";

   }
   /*
    * Creates a list of all the different locations that can be moved to
    */
   public String createLocationList( ){
       String response = "";
      
       //Check left. There will be nothing to the left if X is zero
       if( xPos != 0 ){
            response += world[ yPos ][ xPos - 1 ].equals( blank ) ? "There isn't " +
                "anything to your left.<br/>" : "There is a " +
                world[ yPos ][ xPos - 1].name + " to your left.<br/>";
       }else{
            response += "There isn't anything to your left.<br/>";
       }
 
       //Check right. There will be nothing if at the end of the array
       if( xPos != world[ yPos ].length - 1 ){
            response += world[ yPos ][ xPos + 1 ].equals( blank ) ? "There isn't " +
                "anything to your right.<br/>" : "There is a " +
                world[ yPos ][ xPos + 1 ].name + " to your right.<br/>";
       }else{
           response += "There isn't anything to your right.<br/>";
       }
       //Check front. There will be nothing if Y is zero.
       if( yPos != 0 ){
            response += world[ yPos - 1 ][ xPos ].equals( blank ) ? "There isn't " +
                "anything in front of you.<br/>" : "There is a " +
                world[ yPos - 1 ][ xPos ].name + " in front of you.<br/>";
       }else{
           response += "There isn't anything in front of you.<br/>";
       }
       //Check behind. There will be nothing if at the end of the array.
       if( yPos != world.length - 1 ){
            response += world[ yPos + 1 ][ xPos ].equals( blank ) ? "There isn't " +
                "anything behind you.<br/>" : "There is a " +
                world[ yPos + 1 ][ xPos ].name + " behind you.<br/>";
       }else{
           response += "There isn't anything behind you. <br/>";
       }
       return response;
   }

   /*
    * Lists the items that are currently in the room for pickup
    */
   private String listLocationItems( Location l ){
       String response = "There is ";
        for( int i = 0; i < l.items.size( ); i++ ){
            if( i != 0 ){
                response += " and ";
            }
            response += "a ";
            response += l.items.get( i ).name;
           
        }
        response += " you can take. <br/>";
        return response;
   }
   private String listLocationEnemys( Location l ){
       String response = "A ";
       response += l.enemy.name + " is here.<br/>";
       return response;
   }

    /*
     *Helper function to see who wins fights
     */
    private int playerPower( Player p ){
        int pow = 0;
        for( Item i : p.items ){
            if( i.name.equals( "HATCHET" ) || i.name.equals( "FLOWER" ) ||
                    i.name.equals( "MASSIVE THORN" ) ){
                pow += 1;
                    }
        }
        return pow;
    }

    private String processCommand( String room, String command ) {
      
      String result = mainPage( "" );
      if( player.dead ){
          if(command.equals( "RESTART" ) ){
              restartGame( );
              return generateLandingPage( );
          }else{
              return deathPage( );
          }
          
      }
      if( won ){
          if(command.equals( "RESTART" ) ){
              restartGame( );
              return generateLandingPage( );
          }else{
              return winPage( );
          }
      }

      //Movement
      if ( command.equals( "GO EAST" ) ) {
         if( xPos != world[ yPos ].length - 1 ){
            if( !world[ yPos ][ xPos + 1 ].equals( blank ) ){
                xPos += 1;
                result = mainPage( "You move east." );
            }else{
                result = mainPage( "There isn't anything interesting to " +
                        "the east." );
            }
         }
      }
      if ( command.equals( "GO WEST" ) ) {
         if( xPos != 0 ){
            if( !world[ yPos ][ xPos - 1 ].equals( blank ) ){
                xPos -= 1;
                result = mainPage( "You move west." );
            }else{
                result = mainPage( "There isn't anything interesting to " +
                        "the west." );
            }
         }
      }
      if ( command.equals( "GO NORTH" ) ) {
         if( yPos != 0 ){
            if( !world[ yPos - 1 ][ xPos ].equals( blank ) ){
                yPos -= 1;
                result = mainPage( "You move north." );
            }else{
                result = mainPage( "There isn't anything interesting to " +
                        "the north." );
            }
         }
      }
      if ( command.equals( "GO SOUTH" ) ) {
         if( yPos != world.length - 1 ){
            if( !world[ yPos + 1 ][ xPos ].equals( blank ) ){
                yPos += 1;
                result = mainPage( "You move south." );
            }else{
                result = mainPage( "There isn't anything interesting to " +
                        "the south." );
            }
         }
      }
      //Examine Tile
      if (command.equals( "LOOK" ) ){
          

          result = mainPage( playerLocation( ).description );
          System.out.println(result);
      
      }
      //Pickup Item from tile
      for(int i = 0; i < playerLocation( ).items.size( ); i++ ){
         if ( command.equals( "GET " + playerLocation( ).items.get(i ).name )){
             String s = "You picked up " + 
                 playerLocation( ).items.get( i ).name;
             

             player.AddItem( playerLocation( ).items.get( i ).name,
                     playerLocation( ).items.get( i ).amount );
             playerLocation( ).items.remove( i );
             result = mainPage( s );
           
         }
      }
      //Drop items
      if(command.length( ) > 3){
      if( command.substring( 0, 4 ).equals( "DROP" ) ){
          String r = "";
          
          for( int b = player.items.size( ) - 1; b >= 0; b-- ){
              if( command.equals( "DROP " + player.items.get( b ).name ) ){
                  if( r.equals( "" ) ){
                      r += "Dropped ";
                  }
                  r += player.items.get( b ).name + " x" +
                      player.items.get( b ).amount + "<br/>";
                  playerLocation( ).items.add( player.items.get( b ) );
                  player.items.remove( b );
              
              }
          }
          result = mainPage( r );
      }
      }
      //List inventory
      if( command.equals( "INVENTORY" ) ){
          if( player.items.size( ) == 0 ){
              result = mainPage( "You don't have any items." );
          }
          else{
              String s = "";
              for( int i = 0; i < player.items.size( ); i++ ){
                  s += player.items.get( i ).name + " x" + 
                      player.items.get( i ).amount + "<br/>";
              }
              s += "Current power: " + playerPower( player );
              result = mainPage( s );
          }
      }
      //Fight enemy
      if( command.length( ) > 4 ){
      if( command.substring( 0, 5 ).equals( "FIGHT" ) ){
          if( !playerLocation( ).enemy.equals( Enemy.blank ) ){ 
              if( command.equals( "FIGHT " + playerLocation( ).enemy.name ) ){
                  if( playerPower( player ) >= 
                      playerLocation( ).enemy.power ){
                      result = mainPage( "You defeated " + 
                              playerLocation( ).enemy.name + " and got " + 
                              playerLocation( ).enemy.item.name + "." );
                      player.AddItem( playerLocation( ).enemy.item );
                      playerLocation( ).enemy = Enemy.blank;
                  }else{
                      player.dead = true;
                      result = deathPage( );
                  }

              }
          }else{
              result = mainPage( "There isn't anything to fight." );
          }
          
      }
      }
      
      //Interact with computer
      
      if( command.equals( "USE COMPUTER" ) && playerLocation( ).name.equals(
                  "radar dish" ) ){
          if( player.HasItem( "CONTROL CHIP", 1 ) ){
              result = mainPage( "You calibrate the radar dish. It seems " +
                      "like the navigation system still works." );
              hasComputer = true;
          }else{
              result = mainPage( "You don't have the items to do this." );
          }
      }
      //Use grass
      if( command.equals( "USE GRASS" ) && player.HasItem( "GRASS", 1 ) ){
          result = mainPage( "You throw the grass into the wind. Yippee." );
          player.RemoveItem( "GRASS", 1 );
      }
      if( command.equals( "USE SHIP" ) ){
          if( hasComputer ){
              if( player.HasItem( "WIRES", 1 ) && player.HasItem( "FUEL", 1 ) &&
                      player.HasItem( "NAV SYSTEM", 1 ) ){
                  result = winPage( );
                  won = true;
              }else{
                  result = mainPage( "You don't have all the items needed " +
                          "to use the ship!" );
              }
          }else{
              result = mainPage( "There isn't a point without the nav " +
                      "system being calibrated... " );
          }
      }
      if( command.equals( "HELP" ) ){
         result = mainPage( "GO [CARDINAL DIRECTION] - MOVE</br>GET " +
                 "[ITEM NAME] - PICKUP ITEM</br>DROP [ITEM NAME] - " +
                 "DROP ITEM ON TILE</br>LOOK - EXAMINE CURRENT TILE</br> " +
                 "FIGHT [ENEMY NAME] - TRY AND FIGHT ENEMY</br>USE [ITEM " +
                 "NAME] OR USE [LOCATION OBJECT] - USE AN ITEM OR AN OBJECT " +
                 "AT YOUR LOCATION</br>INVENTORY - VIEW CURRENT ITEMS AND " +
                 "ATTACK POWER" );
      }
        return result;
        
   }

   private void restartGame( ){
        player = new Player( );
        xPos = 1;
        yPos = 0;
        won = false;
        Init( );

   }









}
