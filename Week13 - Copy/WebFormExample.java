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

public class WebFormExample {
   final int PORT = 1234; // Server port 1234 is usually free

   public static void main(String[] args) {
      WebFormExample webFormExample = new WebFormExample ();
      webFormExample.launchServer ( );
   }

   public void launchServer ( ) {
      try {
         HttpServer server = HttpServer.create ( new InetSocketAddress ( PORT ), 0 );
         // Create a new context for handling resource requests
         HttpContext context = server.createContext ( "/" );
         context.setHandler ( this::handleRequest );
         // Accept connections on the specified port.
         server.start ( );
         System.out.printf( "Server started on port %s\n", PORT );
      } catch ( IOException e ) {
         e.printStackTrace ( );
      }
   }

   private void handleRequest ( HttpExchange httpExchange ) throws IOException {
      // Retrieve the requested resource name
      URI uri = httpExchange.getRequestURI ( );
      String room = uri.getPath( ).toUpperCase().substring( 1 );
      String query = uri.getQuery();
      Map<String, String> queryMap = parseQuery( query );
      String command = queryMap.get( "COMMAND" );
      // Compose the response
      String response = "Path: " + room + "\n";
      System.out.printf( "Request room: %s command: %s\n", room, command );
      switch( room ) {
      	case "":
      		response = generateLandingPage( );
      		break;
      	case "ROOM1":
      		response = processCommand( room, command );
      		break;
      	default:
      		response = "Bad Command";
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
            String value = ( keyValue.length > 1 ? keyValue[1] : "" ).toUpperCase( ).replace('+', ' ');
            map.put( key, value );
         }
      }
      return map;
   }
   
   private String generateLandingPage( ) {
      String response = "";
      response += "<p>";
      response += "West of House<br/>";
      response += "You are standing in an open field west of a white house, with a boarded front door.<br/>";
      response += "There is a small mailbox here.";
      response += "</p>";
      
      response += "<form action=\"ROOM1\">";
      response += "  What do you do? <input id=\"COMMAND\" name=\"COMMAND\" type=\"text\"/><br/>";
      response += "  <input id=\"SUBMIT\" name=\"SUBMIT\" value=\"SUBMIT\" type=\"submit\">";
      response += "</form>";
      
      return response;
   }
   
   private String processCommand( String room, String command ) {
      String result = "<p>Nothing happens.</p>";
      if ( command.equals( "GO EAST" ) ) {
         result = "<b>The door is boarded and you can't remove the boards.</b>";
      }
      return result;
   }
}
