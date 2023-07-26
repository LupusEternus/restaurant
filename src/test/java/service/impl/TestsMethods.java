package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;

import java.util.ArrayList;
import java.util.List;

public interface TestsMethods {

    //Helper methods

    public default Meal createVegeMeal() {
        Meal meal = new Meal();
        meal.setName("Salad");
        meal.setDietType(DietType.VEGETARIAN);
        meal.setCalories(100);
        meal.setPrice(20);
        List<Produce> produces = new ArrayList<>();
        produces.add(createVegetable("Onion"));
        produces.add((createVegetable("Tomato")));
        meal.setProducts(produces);
        return meal;
    }

    public default Meal createEmptyMeal() {

        return new Meal();
    }

    public default Meal createRegularMeal() {
        Meal meal = new Meal();
        meal.setName("Chicken Wings");
        meal.setDietType(DietType.REGULAR);
        meal.setCalories(250);
        meal.setPrice(50);
        List<Produce> produces = new ArrayList<>();
        produces.add(createChicken());
        produces.add(createEgg());
        meal.setProducts(produces);
        return meal;
    }

    public default Produce createChicken() {
        Produce produce = new Produce();
        produce.setName("Chicken");
        produce.setProductType(ProductType.MEAT);
        return produce;
    }

    public default Produce createEgg() {
        Produce produce = new Produce();
        produce.setName("Egg");
        produce.setProductType(ProductType.EGGS);
        return produce;
    }

    public default Produce createVegetable(String name) {
        Produce produce = new Produce();
        produce.setName(name);
        produce.setProductType(ProductType.PLANT_BASED);
        return produce;
    }
}
