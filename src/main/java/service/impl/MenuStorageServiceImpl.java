package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import domain.eto.Storage;
import service.exception.NoFoodFoundException;

import java.util.List;
import java.util.Objects;

public class MenuStorageServiceImpl  extends MenuServiceImpl{

    private void canMealBePreparedFromProductsInCommonStorage(Meal meal){
            if(Objects.isNull(meal)){
                throw new NoFoodFoundException();
            }
            if(meal.getProducts().stream().noneMatch(Storage.CommonStorage::checkInStorage)){
                throw new NoFoodFoundException();
            }
    }

    @Override
    public List<Meal> findVegetarianFood(List<Meal> meals) {
        List<Meal> list = super.findVegetarianFood(meals);
        list.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return list;
    }

    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
       List<Meal> list = super.findFoodByType(meals, diet);
       list.forEach(this::canMealBePreparedFromProductsInCommonStorage);
       return list;
    }

    @Override
    public List<Meal> findFoodFoodCheaperThan(List<Meal> meals, Integer price) {
        List<Meal> list = super.findFoodFoodCheaperThan(meals, price);
        list.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return list;
    }

    @Override
    public List<Meal> findFoodWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        List<Meal> list = super.findFoodWithCalories(meals, minCalories, maxCalories);
        list.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return list;
    }

    @Override
    public List<Meal> findFoodByName(List<Meal> meals, String name) {
        List<Meal> list = super.findFoodByName(meals, name);
        list.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return list;
    }

    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Produce product) {
        List<Meal> list = super.findFoodContaining(meals, product);
        list.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return list;
    }

    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Produce> products) {
        List<Meal> list = super.findFoodExcludingAll(meals, products);
        list.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return list;
    }
}
