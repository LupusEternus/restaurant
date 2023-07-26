package service.impl;

import domain.eto.Meal;
import domain.eto.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.exception.NoFoodFoundException;

import static org.junit.jupiter.api.Assertions.fail;

public class StorageServiceImplTest implements TestsMethods {

    StorageServiceImpl storageService;

    @BeforeEach
    public void setUp(){
        this.storageService = new StorageServiceImpl();
    }


    @Test
    public void shouldNotThrowExceptionFromCanMealBePreparedFromProductsInStorage(){
        Meal meal = createRegularMeal();
        Storage storage = new Storage();
        storage.addProduce(createChicken());
        storage.addProduce(createEgg());

        Assertions.assertDoesNotThrow(() ->storageService.canMealBePreparedFromProductsInStorage(meal,storage));
    }

    @Test
    public void shouldThrowExceptionFromCanMealBePreparedFromProductsInStorage(){
        Meal meal = createRegularMeal();
        Storage storage = new Storage();
        storage.addProduce(createVegetable("Onion"));
        storage.addProduce(createVegetable("Flowers"));

        Assertions.assertThrows(NoFoodFoundException.class,() ->storageService.canMealBePreparedFromProductsInStorage(meal,storage));
    }

    @Test
    public void shouldThrowExceptionFromCanMealBePreparedFromProductsInStorageWithNulls(){
        Meal meal = null;
        Storage storage = null;

        Assertions.assertThrows(NoFoodFoundException.class,() ->storageService.canMealBePreparedFromProductsInStorage(meal,storage));
    }

}
