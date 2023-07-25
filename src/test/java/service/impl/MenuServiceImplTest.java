package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class MenuServiceImplTest {

    private final MenuServiceImpl menu = new MenuServiceImpl();
    private  List<Meal> meals;
    private Meal vegeMeal_1;
    private Meal regularMeal_1;
    private Meal vegeMeal_2;
    private Meal regularMeal_2;




    @BeforeEach
    public void SetUp(){
        meals = new ArrayList<>();
        vegeMeal_1 = createVegeMeal();
        vegeMeal_2 = createVegeMeal();
        regularMeal_1 = createRegularMeal();
        regularMeal_2 = createRegularMeal();

    }



    @Test
    public void shouldFindVegetarianFood() {

        meals.add(vegeMeal_1);
        meals.add(regularMeal_1);

        Assertions.assertEquals(1, menu.findVegetarianFood(meals).size());
    }


    @Test
    public void shouldNotFindVegetarianFood() {

        meals.add(regularMeal_1);
        Assertions.assertTrue(menu.findVegetarianFood(meals).isEmpty());

    }

    @Test
    public void shouldReturnEmptyListWhenMealIsNull(){
        meals.add(createEmptyMeal());
        Assertions.assertEquals(0, menu.findVegetarianFood(meals).size());
    }

    @Test
    public void shouldFindFoodByType(){
        meals.add(vegeMeal_1);
        meals.add(regularMeal_1);
        meals.add(regularMeal_2);

        Assertions.assertEquals(2,menu.findFoodByType(meals,DietType.REGULAR).size());
    }
    @Test
    public void shouldNotFindFoodByType(){
        meals.add(vegeMeal_1);

        Assertions.assertEquals(0,menu.findFoodByType(meals,DietType.REGULAR).size());
    }

    @Test
    public void shouldFindFoodCheaperThan(){

        regularMeal_1.setPrice(199);
        regularMeal_2.setPrice(250);
        vegeMeal_1.setPrice(47);
        meals.add(regularMeal_1);
        meals.add(regularMeal_2);
        meals.add(vegeMeal_1);
        Integer topPrice = 200;

        Assertions.assertEquals(2,menu.findFoodFoodCheaperThan(meals,topPrice).size());
    }
    @Test
    public void shouldNotFindFoodCheaperThan(){

        meals.add(regularMeal_1);
        meals.add(regularMeal_2);
        meals.add(vegeMeal_1);
        Integer topPrice = 1;
        Assertions.assertTrue(menu.findFoodFoodCheaperThan(meals,topPrice).isEmpty());

    }
    @Test
    public void shouldFindFoodWithCalories(){
        regularMeal_1.setCalories(350);
        regularMeal_2.setCalories(500);
        vegeMeal_1.setCalories(250);
        vegeMeal_2.setCalories(125);
        meals.add(regularMeal_1);
        meals.add(regularMeal_2);
        meals.add(vegeMeal_1);
        meals.add(vegeMeal_2);

        Assertions.assertEquals(2,menu.findFoodWithCalories(meals,130,400).size());

    }
    @Test
    public void shouldNotFindFoodWithCalories(){
        regularMeal_1.setCalories(350);
        regularMeal_2.setCalories(500);
        vegeMeal_1.setCalories(250);
        vegeMeal_2.setCalories(125);
        meals.add(regularMeal_1);
        meals.add(regularMeal_2);
        meals.add(vegeMeal_1);
        meals.add(vegeMeal_2);

        Assertions.assertEquals(0,menu.findFoodWithCalories(meals,0,100).size());

    }

    @Test
    public void shouldFindFoodByNameWithExactlySameName(){
        String foodName = "Salad";
        meals.add(regularMeal_1);
        meals.add(vegeMeal_1);
        Assertions.assertEquals(1,menu.findFoodByName(meals,foodName).size());
    }
    @Test
    public void shouldFindFoodByNameWithCaseDifference(){
        String foodName = "salad";
        vegeMeal_2.setName("Salad With Beans");
        meals.add(vegeMeal_2);
        meals.add(vegeMeal_1);
        Assertions.assertEquals(2,menu.findFoodByName(meals,foodName).size());
    }
    @Test
    public void shouldFindFoodContaining(){

        regularMeal_1.getProducts().add(createChicken());
        meals.add(regularMeal_1);
        Assertions.assertEquals(1,menu.findFoodContaining(meals,createChicken()).size());
    }
    @Test
    public void shouldNotFindFoodContaining(){

        meals.add(regularMeal_1);
        Assertions.assertEquals(1,menu.findFoodContaining(meals,createChicken()).size());
    }
    @Test
    public void shouldFindFoodExcludingAll(){
        meals.add(regularMeal_1);
        meals.add(vegeMeal_1);
        List<Produce> produces = new ArrayList<>();
        produces.add(createVegetable("Beans"));
        produces.add(createVegetable("Carrot"));

        Assertions.assertEquals(2,menu.findFoodExcludingAll(meals,produces).size());
    }

    @Test
    public void shouldNotFindFoodExcludingAll(){
        meals.add(regularMeal_1);
        meals.add(vegeMeal_1);
        List<Produce> produces = new ArrayList<>();
        produces.add(createVegetable("Onion"));
        produces.add(createEgg());

        Assertions.assertEquals(0,menu.findFoodExcludingAll(meals,produces).size());
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