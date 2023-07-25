package domain;

public enum DietType {

  VEGETARIAN(true),
  VEGAN(true),
  REGULAR(false);

  final boolean isVegetarian;
  DietType(boolean isVegetarian){
    this.isVegetarian = isVegetarian;
  }
  public boolean isVegetarian(){
    return isVegetarian;
  }

}
