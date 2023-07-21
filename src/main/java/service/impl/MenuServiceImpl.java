package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import service.api.MenuService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MenuServiceImpl implements MenuService {

    @Override
    public List<Meal> findVegetarianFood(List<Meal> meals) {
        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> Objects.nonNull(meal.getDietType()) && meal.getDietType().equals(DietType.VEGETARIAN))
                .filter(meal -> meal.getDietType().equals(DietType.VEGETARIAN))
                .collect(Collectors.toList());

    }

    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> Objects.nonNull(meal.getDietType()))
                .filter(meal -> meal.getDietType().equals(diet))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> findFoodByName(List<Meal> meals, Integer price) {
        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> meal.getPrice() < price)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> findFoodCheaperWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> meal.getCalories() > minCalories && meal.getCalories() < maxCalories)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> findFoodByName(List<Meal> meals, String name) {
        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> Objects.nonNull(meal.getName()))
                .filter(meal -> meal.getName().toLowerCase().startsWith(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Produce product) {
        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> meal.getProducts().contains(product))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Produce> products) {
        return meals.stream()
                .filter(meal -> Collections.disjoint(meal.getProducts(),products))
                .collect(Collectors.toList());
    }
}
