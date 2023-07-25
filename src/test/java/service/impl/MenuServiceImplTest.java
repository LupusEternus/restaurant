package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

class MenuServiceImplTest {

    private final MenuServiceImpl menu = new MenuServiceImpl();
    private final List<Meal> meals = new ArrayList<>();


    @Test
    public void shouldFindVegetarianFood() {

        meals.add(createVegeMeal());
        meals.add(createRegularMeal());

        Assertions.assertEquals(1, menu.findVegetarianFood(meals).size());
    }


    @Test
    public void shouldNotFindVegetarianFood() {

        meals.add(createRegularMeal());
        Assertions.assertTrue(menu.findVegetarianFood(meals).isEmpty());

    }

    @Test
    public void shouldReturnEmptyListWhenMealIsNull(){
        meals.add(createEmptyMeal());
        Assertions.assertEquals(0, menu.findVegetarianFood(meals).size());
    }

    @Test
    public void shouldFindFoodByType(){
        meals.add(createVegeMeal());
        meals.add(createRegularMeal());
        meals.add(createRegularMeal());

        Assertions.assertEquals(2,menu.findFoodByType(meals,DietType.REGULAR).size());
    }
    @Test
    public void shouldNotFindFoodByType(){
        meals.add(createVegeMeal());

        Assertions.assertEquals(0,menu.findFoodByType(meals,DietType.REGULAR).size());
    }

    @Test
    public void shouldFindFoodCheaperThan(){
        Meal meal = createRegularMeal();
        Meal meal1 = createRegularMeal();
        Meal meal2 = createVegeMeal();
        meal.setPrice(199);
        meal1.setPrice(250);
        meal2.setPrice(47);
        meals.add(meal);
        meals.add(meal1);
        meals.add(meal2);
        Integer topPrice = 200;

        Assertions.assertEquals(2,menu.findFoodFoodCheaperThan(meals,topPrice).size());
    }
    @Test
    public void shouldNotFindFoodCheaperThan(){

        meals.add(createRegularMeal());
        meals.add(createRegularMeal());
        meals.add(createRegularMeal());
        Integer topPrice = 1;
        Assertions.assertTrue(menu.findFoodFoodCheaperThan(meals,topPrice).isEmpty());

    }
    @Test
    public void shouldFindFoodWithCalories(){
        fail();

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

    public Meal createRegularMeal() {
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