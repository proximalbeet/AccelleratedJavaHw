import java.util.List;
import java.util.ArrayList;
public class Player{
    public List< Item > items;
    public int power = 0;
    public boolean dead = false;
    public Player( ){
        items = new ArrayList< Item > ( );
    }
    public void AddItem( String name, int amount ){
        for( int i = 0; i < items.size( ); i++ ){
            if( items.get( i ).name.equals( name ) ){
                items.get( i ).amount += amount;
                return;
            }
        }
        items.add( new Item( name, amount ) );

    }
    public void AddItem( Item i ){
        items.add( i );
    }
    public Boolean HasItem( String name, int amount ){
        int neededCount = amount;
        for( int i = 0; i < items.size( ); i++ ){
            if( items.get( i ).name.equals( name ) ){
                neededCount -= items.get( i ).amount;
            }
        }
        return neededCount <= 0;
    }
    public void RemoveItem( String name, int amount ){
        
        for( int i = 0; i < items.size( ); i++ ){
            if( items.get( i ).name.equals( name ) ){
                int amountToRemove = items.get( i ).amount;
                if( amount > amountToRemove ){
                    amount -= amountToRemove;
                    
                    items.get( i ).amount = 0;
                    if( amount <= 0 ){
                        break;
                    }
                }else{
                    items.get( i ).amount -= amount;
                    break;
                }
            }
        }
        for( int i = items.size( ) - 1; i >= 0; i-- ){
            if( items.get( i ).amount <= 0 ){
                items.remove ( i );
            }
        }
    }
}
