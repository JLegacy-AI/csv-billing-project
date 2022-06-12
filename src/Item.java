
/**
 * Item - This class work as a single record for static Database Table (item)
 * This contain category type cat - price - quantity
 * @version (v1.0 - 4/28/22)
 */
public class Item
{
    private int price;
    private int quantity;
    private Category category;

    public Item(Builder build)
    {
        this.price = build.price;
        this.quantity =  build.quantity;
        this.category = build.category;
    }
    
    public int getPrice(){
        return this.price;
    }
    
    public int getQuantity(){
        return this.quantity;
    }

    public Category getCategory(){
        return this.category;
    }
    
    private String getCategory(Category cat){
        switch(cat){
            case ESSENTIALS:
                return "Essentials";
            case LUXUARY:
                return "Luxuary";
            case MISC:
                return "Misc";
        }
        return null;
    }
    
    @Override
    public String toString(){
        return "[ "+getCategory(this.category)+", "+this.price+", "+this.quantity+" ]";
    }
    
    public static class Builder{
        public int price;
        public int quantity;
        public Category category;
        
        public Builder(Category cat){
            this.category = cat;
        }
        
        public Builder setPrice(int price){
            this.price = price;
            return this;
        }
        
        public Builder setQuantity(int quantity){
            this.quantity = quantity;
            return this;
        }
        
        public Item buildItem(){
            return new Item(this);
        }
    }
}
