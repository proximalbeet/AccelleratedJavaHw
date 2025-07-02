public class Item{
    public String name;
    public int amount;
    public int value = 0;
    public Item( String s, int i ){
        name = s;
        amount = i;
    }
    public Item( String s, int i, int v ){
        name = s;
        amount = i;
        value = v;
    }
}
