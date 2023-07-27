package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import domain.eto.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.exception.NoFoodFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class MenuStorageServiceImplTest implements TestsMethods{

    private MenuStorageServiceImpl menuStorageService;
    private List<Meal> meals;
    private Meal vegetarianMeal_1;
    private Meal regularMeal_1;
    private Meal veganMeal_1;
    private Meal regularMeal_2;

    @BeforeEach
    public void setUp(){

        menuStorageService = new MenuStorageServiceImpl();
        meals = new ArrayList<>();
        vegetarianMeal_1 = createVegetarianMeal();
        veganMeal_1 = createVeganMeal();
        regularMeal_1 = createRegularMeal();
        regularMeal_2 = createRegularMeal();
        Storage.CommonStorage.clearStorage();
        Storage.CommonStorage.addToStorage(createChicken(),10);
        Storage.CommonStorage.addToStorage(createEgg(),10);
        Storage.CommonStorage.addToStorage(createVegetable("Onion"),10);
        Storage.CommonStorage.addToStorage(createVegetable("Tomato"),10);
        Storage.CommonStorage.addToStorage(createVegetable("Apple"),10);

    }

    @Test
    public void shouldFindVegetarianFood(){
        meals.add(regularMeal_1);
        meals.add(vegetarianMeal_1);
        Assertions.assertDoesNotThrow(() ->menuStorageService.findVegetarianFood(meals));
        Assertions.assertFalse(menuStorageService.findVegetarianFood(meals).isEmpty());
        Assertions.assertEquals(1,menuStorageService.findVegetarianFood(meals).size());

    }

    @Test
    public void shouldThrowExceptionAfterNotFindingProducesFromFindVegetarianFood(){
        meals.add(vegetarianMeal_1);
        Storage.CommonStorage.clearStorage();
        Assertions.assertThrowsExactly(NoFoodFoundException.class,()->menuStorageService.findVegetarianFood(meals));
    }

    @Test
    public void shouldFindFoodByType() {
        meals.addAll(List.of(vegetarianMeal_1,regularMeal_1,regularMeal_2));
        Assertions.assertEquals(1,menuStorageService.findFoodByType(meals, DietType.VEGETARIAN).size());
    }
    @Test
    public void shouldThrowExceptionAfterNotFindingProducesFromFindFoodByTypeResultList()  {
        meals.addAll(List.of(regularMeal_2,regularMeal_1,vegetarianMeal_1));
        Storage.CommonStorage.clearStorage();
        Assertions.assertThrowsExactly(NoFoodFoundException.class,()->menuStorageService.findVegetarianFood(meals));

    }
    @Test
    public void shouldFindFoodFoodCheaperThan(){
        meals.addAll(List.of(regularMeal_1,veganMeal_1));
        Assertions.assertEquals(1,menuStorageService.findFoodFoodCheaperThan(meals,30).size());
    }
    @Test
    public void shouldThrowExceptionAfterNotFindingProducesFromFindFoodFoodCheaperThan(){
        meals.add(veganMeal_1);
        Storage.CommonStorage.clearStorage();
        Assertions.assertThrowsExactly(NoFoodFoundException.class,() -> menuStorageService.findFoodFoodCheaperThan(meals,30));
    }
    @Test
    public void shouldFindFoodWithCalories(){
        meals.addAll(List.of(regularMeal_1,vegetarianMeal_1,veganMeal_1));
        Assertions.assertEquals(2,menuStorageService.findFoodWithCalories(meals,40,110).size());
    }
    @Test
    public void shouldThrowExceptionAfterNotFindingProducesFromFindFoodWithCalories(){
        meals.addAll(List.of(regularMeal_1,vegetarianMeal_1,veganMeal_1));
        Storage.CommonStorage.clearStorage();
        Assertions.assertThrowsExactly(NoFoodFoundException.class,() -> menuStorageService.findFoodWithCalories(meals,30,110));
    }

    @Test
    public void shouldFindFoodByName(){
        meals.addAll(List.of(regularMeal_1,vegetarianMeal_1,veganMeal_1));
        Assertions.assertEquals(1,menuStorageService.findFoodByName(meals,"Chicken Wings").size());
    }
    @Test
    public void shouldThrowExceptionAfterNotFindingProducesFromFindFoodByName(){
        meals.addAll(List.of(regularMeal_1,vegetarianMeal_1,veganMeal_1));
        Storage.CommonStorage.clearStorage();
        Assertions.assertThrowsExactly(NoFoodFoundException.class,() -> menuStorageService.findFoodByName(meals,"Chicken Wings"));

    }

    @Test
    public void shouldFindFoodContaining(){
        meals.addAll(List.of(regularMeal_1,vegetarianMeal_1,veganMeal_1));
        Produce produce = createChicken();
        Assertions.assertEquals(1,menuStorageService.findFoodContaining(meals,produce).size());
    }
    @Test
    public void shouldThrowExceptionAfterNotFindingProducesFromFindFoodContaining(){
        meals.addAll(List.of(regularMeal_1,vegetarianMeal_1,veganMeal_1));
        Produce produce = createChicken();
        Storage.CommonStorage.removeFromStorage(createChicken(),10);
        Assertions.assertThrowsExactly(NoFoodFoundException.class,() -> menuStorageService.findFoodContaining(meals,produce));

    }
    @Test
    public void shouldFindFoodExcludingAll(){
        meals.addAll(List.of(regularMeal_1,vegetarianMeal_1));
        List<Produce> produceList = new ArrayList<>(List.of(createChicken()));
        Assertions.assertEquals(1,menuStorageService.findFoodExcludingAll(meals,produceList).size());
    }
    @Test
    public void shouldThrowExceptionAfterNotFindingProducesFromFindFoodExcludingAll(){
        meals.addAll(List.of(regularMeal_1,vegetarianMeal_1));
        List<Produce> produceList = new ArrayList<>(List.of(createChicken()));
        Storage.CommonStorage.clearStorage();
        Assertions.assertThrowsExactly(NoFoodFoundException.class,() -> menuStorageService.findFoodExcludingAll(meals,produceList));
    }








}
