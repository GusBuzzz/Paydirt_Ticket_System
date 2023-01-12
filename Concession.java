public class Concession{
  private float popcornPrice;
  private float sodaPrice;
  private float hotDogPrice;

  Concession(){
    popcornPrice = 6.00f;
    sodaPrice = 1.50f;
    hotDogPrice = 4.00f;
  }

  //Getters
  public float getPopcornPrice(){
    return popcornPrice;
  }

  public float getSodaPrice(){
    return sodaPrice;
  }

  public float getHotDogPrice(){
    return hotDogPrice;
  }

  //set concession tax
  public void setTaxPrice(float concessionTax){
    popcornPrice = popcornPrice * concessionTax;
    sodaPrice = sodaPrice * concessionTax;
    hotDogPrice = hotDogPrice * concessionTax;
  }

  public String toString(){
    return "POPCORN PRICE: $" + String.format("%.2f", getPopcornPrice()) + "\nSODA PRICE: $" +
    String.format("%.2f", getSodaPrice()) + "\nHOT DOG PRICE: $" + String.format("%.2f", getHotDogPrice());
  }
}
