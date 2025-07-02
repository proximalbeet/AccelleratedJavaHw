public class Enemy{
    public Item item;
    public String name;
    public int power;
    public Enemy( String n, int p ){
        name = n;
        power = p;
    }
    public static final Enemy blank = new Enemy( "BLANK", 0 );
}
