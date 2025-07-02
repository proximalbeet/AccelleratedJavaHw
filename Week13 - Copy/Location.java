import java.util.List;
import java.util.ArrayList;
public class Location{
    public String name = "";
    public String description = "";
    public List< Item > items = new ArrayList< Item >( );
    public Enemy enemy = Enemy.blank;
    public Location( String s, String d ){
        name = s;
        description = d;
    }
    public Location( String s, List< Item > itms ){
        name = s;
        items = itms;
    }

}
