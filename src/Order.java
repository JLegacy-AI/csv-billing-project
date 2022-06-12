
/**
 * This class Store Single Record of Order Table
 * This Table Created to Store Order info
 * Contain Three Varibles item - quantity - card number
 * @version (v1.0 - 4/29/22)
 */
public class Order
{
    private String item;
    private int quantity;
    private String cardNumber;
    
    public Order(Builder build){
        this.item = build.item;
        this.quantity = build.quantity;
        this.cardNumber = build.cardNumber;
    }
    
    public String getItem(){
        return this.item;
    }
    
    public String getCardNumber(){
        return this.cardNumber;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    
    @Override
    public String toString(){
        return "[ "+this.item+", "+this.quantity+", "+this.cardNumber+" ]";
    }
    
    public static class Builder{
        
        private String item;
        private int quantity;
        private String cardNumber;
        
        public Builder(String item){
            this.item=item;
        }
        
        public Builder setQuantity(int quantity){
            this.quantity = quantity;
            return this;
        }
        
        public Builder setCardNumber(String cardNumber){
            this.cardNumber = cardNumber;
            return this;
        }
        
        public Order build(){
            return new Order(this);
        }
    }
}
