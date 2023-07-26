package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import domain.eto.Storage;
import service.exception.NoFoodFoundException;

import java.util.List;
import java.util.Objects;

public class MenuStorageServiceImpl  extends MenuServiceImpl{

    private void canMealBePreparedFromProductsInStorage(Meal meal, Storage storage){
            if(Objects.isNull(meal) || Objects.isNull(storage)){
                throw new NoFoodFoundException();
            }
            if(meal.getProducts().stream().noneMatch(Storage.CommonStorage::checkInStorage)){

            }

    }

    @Override
    public List<Meal> findVegetarianFood(List<Meal> meals) {
        return super.findVegetarianFood(meals);
    }

    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
        return super.findFoodByType(meals, diet);
    }

    @Override
    public List<Meal> findFoodFoodCheaperThan(List<Meal> meals, Integer price) {
        return super.findFoodFoodCheaperThan(meals, price);
    }

    @Override
    public List<Meal> findFoodWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        return super.findFoodWithCalories(meals, minCalories, maxCalories);
    }

    @Override
    public List<Meal> findFoodByName(List<Meal> meals, String name) {
        return super.findFoodByName(meals, name);
    }

    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Produce product) {
        return super.findFoodContaining(meals, product);
    }

    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Produce> products) {
        return super.findFoodExcludingAll(meals, products);
    }
}
