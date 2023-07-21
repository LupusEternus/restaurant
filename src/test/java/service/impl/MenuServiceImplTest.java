package service.impl;

import domain.eto.Meal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuServiceImplTest {

    private MenuServiceImpl menu = new MenuServiceImpl();

    @Test
    public void shouldFindVegetarianFood() {

        List<Meal> meals = new ArrayList<>();
        meals.add(createVegeMeal());
        meals.add(createNonVegeMeal());

        Assertions.assertEquals(1, menu.findVegetarianFood(meals).size());
    }


    public Meal createVegeMeal(){

    }

}