
import java.util.*;

/**
 * Purpose of this Interface to provide scalibilty to application
 * @version (v1.0 - 4/29/22)
 */
public interface Inventory
{
    String getName();
    String listAllItems();
    String listAllCards();
    String orderOneItem(Order order);
    String orderByCSV(String filePath);
}
