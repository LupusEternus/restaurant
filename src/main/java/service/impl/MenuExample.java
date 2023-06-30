package service.impl;

import java.util.ArrayList;
import java.util.List;

import domain.DietType;
import domain.eto.Meal;
import service.api.MenuService;
import service.exception.NoFoodFoundException;

/**
 * This is just a showcase
 */
public class MenuExample {

  /**
   * This is just showcase to tell you how to implement most basic operations in JAVA.
   * In productive apps that Use Spring and JPA, similar functionalities are implemented by writing a proper query.
   * @param meals Input list
   * @return List of meals that are  of type vegetarian
   */
  public List<Meal> findVegetarianFood(List<Meal> meals) {
    //This is an example
    List<Meal> filteredMeals = new ArrayList<Meal>(); // initialize array
    for (Meal meal : meals) { //create for loop
      // create if condition to handle nulls
      if(meal != null){
        //check dietType
        if(meal.getDietType() == DietType.VEGETARIAN){
          //add found object to resulting, filtered list
          filteredMeals.add(meal);
        }
      }
    }

    //Hint: this is just an showcase of a custom exception;
    if(filteredMeals.isEmpty()){
      throw new NoFoodFoundException();
    }
    return filteredMeals;
  }

}
