package domain.eto;

import service.exception.NoFoodFoundException;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private Map<Produce, Integer> inventory;

    public Storage() {
        inventory = new HashMap<>();
    }

    public void addProduce(Produce produce) {

        if (inventory.containsKey(produce)) {
            inventory.put(produce, inventory.get(produce) + 1);
        } else {
            inventory.put(produce, 1);
        }
    }
    public Integer getQuantityOfProduce(Produce produce){
        return inventory.getOrDefault(produce,0);
    }


    public Map<Produce, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Produce, Integer> inventory) {
        this.inventory = inventory;
    }

    public static class CommonStorage{

        private static Storage storage = new Storage();

        public static boolean checkInStorage(Produce produce){
            return storage.inventory.containsKey(produce) && storage.inventory.get(produce) > 0;
        }

        public static void addToStorage(Produce produce,Integer quantity){
            if(storage.inventory.containsKey(produce)){
                storage.inventory.put(produce,storage.inventory.get(produce) + quantity);
            }else
                storage.inventory.put(produce, quantity);
        }

        public static void removeFromStorage(Produce produce,Integer quantity){
            if(storage.inventory.containsKey(produce) && storage.inventory.get(produce) >= quantity){
                storage.inventory.put(produce,storage.inventory.get(produce) - quantity);
            }else {
                throw new NoFoodFoundException();
            }
        }
        public static void clearStorage(){
            storage = new Storage();
        }




    }
}
