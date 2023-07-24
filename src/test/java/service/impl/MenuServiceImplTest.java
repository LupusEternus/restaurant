package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.exception.NoFoodFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

class MenuServiceImplTest {

    private final MenuServiceImpl menu = new MenuServiceImpl();
    private final List<Meal> meals = new ArrayList<>();


    @Test
    public void shouldFindVegetarianFood() {

        meals.add(createVegeMeal());
        meals.add(createNonVegeMeal());

        Assertions.assertEquals(1, menu.findVegetarianFood(meals).size());
    }


    @Test
    public void shouldNotFindVegetarianFood() {

        meals.add(createNonVegeMeal());
        Assertions.assertTrue(menu.findVegetarianFood(meals).isEmpty());

    }

    @Test
    public void shouldReturnEmptyListWhenMealIsNull(){
        meals.add(createEmptyMeal());
        Assertions.assertEquals(0, menu.findVegetarianFood(meals).size());
    }


    public Meal createVegeMeal() {
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
    public Meal createEmptyMeal() {

        return new Meal();
    }

    public Meal createNonVegeMeal() {
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

    public Produce createChicken() {
        Produce produce = new Produce();
        produce.setName("Chicken");
        produce.setProductType(ProductType.MEAT);
        return produce;
    }

    public Produce createEgg() {
        Produce produce = new Produce();
        produce.setName("Egg");
        produce.setProductType(ProductType.EGGS);
        return produce;
    }

    public Produce createVegetable(String name) {
        Produce produce = new Produce();
        produce.setName(name);
        produce.setProductType(ProductType.PLANT_BASED);
        return produce;
    }


}