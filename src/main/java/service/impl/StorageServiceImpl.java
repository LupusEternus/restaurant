package service.impl;

import domain.eto.Meal;
import domain.eto.Produce;
import domain.eto.Storage;
import service.api.StorageService;
import service.exception.NoFoodFoundException;

public class StorageServiceImpl implements StorageService {


    @Override
    public void canMealBePreparedFromProductsInStorage(Meal meal, Storage storage) {
        if(meal == null || storage == null){
            throw new NoFoodFoundException();
        }
        for (Produce produce : meal.getProducts()){
            if(storage.getQuantityOfProduce(produce) == 0){
                throw new  NoFoodFoundException();
            }
        }
    }
}
