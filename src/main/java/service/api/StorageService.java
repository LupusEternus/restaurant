package service.api;

import domain.eto.Meal;
import domain.eto.Storage;

public interface StorageService {

    public void canMealBePreparedFromProductsInStorage(Meal meal, Storage storage);


}
