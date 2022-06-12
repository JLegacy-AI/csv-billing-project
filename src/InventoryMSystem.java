
/**
 * InventoryMSystem class Implements Inventory Interface
 * @version (v1.0 - 4/29/22 )
 */

import java.util.*;

public class InventoryMSystem implements Inventory
{
    private String name;
    private OrderRecord or = OrderRecord.getInstance();
    private ArrayList<Order> ordersList;
    private HashMap<String, Item> itemsList;
    private ArrayList<Card> cardsList;
    
    public InventoryMSystem(String name){
        this.name = name;
        ordersList = new ArrayList<>();
        cardsList = new ArrayList<>();
        itemsList = new HashMap<>();
        loadItems();
        loadCards();
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    /**
     * @return - String details of all Items present to over inventory System
     */
    
    @Override
    public String listAllItems(){
        String details="";
        for(Map.Entry<String, Item> maps: itemsList.entrySet()){
            details+=maps.getKey()+" ".repeat(20-maps.getKey().length())+maps.getValue().toString()+"\n";
        }
        return details;
    }
    
    @Override
    public String listAllCards(){
        String details = "";
        for(Card card: cardsList){
            details+=card.toString()+"\n";
        }
        return details;
    }
    
    /**
     * This method do some validation on order Placed
     * check item name is correct
     * check orders quantity limit
     * Update the present Quantity of Object After Ordered Placed
     * @params - Order class instance
     * @return - String class instance
     */
    
    @Override
    public String orderOneItem(Order order){
        
        if(itemsList.get(order.getItem())==null){
            or.recordError("Wrong Item Name");
            return "Wrong Item Name";
        }
        
        if(!checkQuantity(order)){
            return "Item is not in inventory";
        }
        
        if(validateOrderQuantity(order)){
            ordersList.add(order);
            updateQuantity(order);
            if(!cardExists(order.getCardNumber())){
                cardsList.add(new Card.Builder(order.getCardNumber()).buildCard());
            }
            or.record(order, itemsList.get(order.getItem()));
            return "Ordered Successfully";
        }else{
            or.recordError("Please correct quantities");
            return "Please correct quantities";    
        }
    }

    /**
     * @params - String instance which filePath of CSV file
     * @return - String instance that order "Done"
     */
    
    @Override
    public String orderByCSV(String filePath){
        for(Order ord : or.convertCSV(filePath)){
            orderOneItem(ord);
        }
        return "Done";
    }
    
    /**
     *  This Method validate some conditions but it used only in this class
     *  Essentials must be less than euqal to 3 are allowed in one order
     *  Luxuary <=4 / > 4 are Allowed
     *  Misc <=6 / > 6 are allowed
     *  @return - true only if ordered item met its limited quantity
     *  @params - Order object which is ordered
     */
    
    private boolean validateOrderQuantity(Order ord){
        //Here Get First OrderedItem to furthur Validation of Quantity
        //This Local Variable only intended to make code readAble Only
        Item orderedItem = itemsList.get(ord.getItem());
        switch(orderedItem.getCategory()){
            case ESSENTIALS:
                if(ord.getQuantity()>3){
                    return false;   
                }
                break;
            case LUXUARY:
                if(ord.getQuantity()>4){
                    return false;   
                }
                break;
            case MISC:
                if(ord.getQuantity()>6){
                    return false;   
                }
                break;
        }
        return true;
    }
    
    private boolean cardExists(String cardNumber){
        for(Card c: cardsList){
            if(c.getCardNumber().equalsIgnoreCase(cardNumber)){
                return true;
            }
        }
        return false;
    }
    
    private void updateQuantity(Order ord){
        Item orderItem = itemsList.get(ord.getItem());
        orderItem = new Item.Builder(orderItem.getCategory()).setPrice(orderItem.getPrice()).setQuantity(orderItem.getQuantity()-ord.getQuantity()).buildItem();
        itemsList.put(ord.getItem(), orderItem);
    }
    
    /**
     * 
     * @params order class
     * @return boolean 
     */
    private boolean checkQuantity(Order ord){
        if(itemsList.get(ord.getItem()).getQuantity()>=ord.getQuantity()){
            return true;
        }else{
            return false;
        }
    }
    
    private void loadItems(){
        //Essentials objects adding in Hashmaps
        itemsList.put("soap",new Item.Builder(Category.ESSENTIALS)
                                                    .setPrice(20)
                                                    .setQuantity(15)
                                                    .buildItem());
        itemsList.put("clothes",new Item.Builder(Category.ESSENTIALS).setPrice(7).setQuantity(15).buildItem());
        itemsList.put("milk",new Item.Builder(Category.ESSENTIALS).setPrice(6).setQuantity(15).buildItem());
        //Luxuary objects adding in Hashmaps
        itemsList.put("perfume",new Item.Builder(Category.LUXUARY).setPrice(12).setQuantity(15).buildItem());
        itemsList.put("chocolates",new Item.Builder(Category.LUXUARY).setPrice(10).setQuantity(15).buildItem());
        //Miscelleneous objects adding in Hashmaps
        itemsList.put("bedsheets",new Item.Builder(Category.MISC).setPrice(9).setQuantity(15).buildItem());
        itemsList.put("footwear",new Item.Builder(Category.MISC).setPrice(8).setQuantity(15).buildItem());
    }
    
    private void loadCards(){
        //Cards Adding in ArrayList of Card Objects
        cardsList.add(new Card.Builder("ABC123456").buildCard());
        cardsList.add(new Card.Builder("DEF123456").buildCard());
        cardsList.add(new Card.Builder("GHI123456").buildCard());
    }
}
