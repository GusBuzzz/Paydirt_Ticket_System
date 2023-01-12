public class Event{
  private String eventName;
  private String venueName;
  private String address;
  private float ticketPrice;
  private int availableTickets;
  private Concession concessionStand;

  Event(){
  }
  
  Event(String eventName, String venueName, String address, float ticketPrice,
   int availableTickets, Concession concessionStand){
     this.eventName = eventName;
     this.venueName = venueName;
     this.address = address;
     this.ticketPrice = ticketPrice;
     this.availableTickets = availableTickets;
     this.concessionStand = concessionStand;
  }

  //Setters
  public void setEventName(String eventName){
    this.eventName = eventName;
  }

  public void setAddress(String address){
    this.address = address;
  }

  public void setVenueName(String venueName){
    this.venueName = venueName;
  }

  public void setTicketPrice(float ticketPrice){
    this.ticketPrice = ticketPrice;
  }

  public void setAvailableTickets(int availableTickets){
    this.availableTickets = availableTickets;
  }

  public void setConcessionStand(Concession concessionStand){
    this.concessionStand = concessionStand;
  }

  //Getters
  public String getEventName(){
    return eventName;
  }

  public String getAddress(){
    return address;
  }

  public String getVenueName(){
    return venueName;
  }

  public float getTicketPrice(){
    return ticketPrice;
  }

  public int getAvailableTickets(){
    return availableTickets;
  }

  public Concession getConcessionStand(){
    return concessionStand;
  }

  //Is Valid
  private boolean isValid(int ticketsbought){
    if (ticketsbought <= availableTickets){
      return true;
    }
    else{
      return false;
    }
  }

  // purchase Tickets
  public double purchaseTickets(int ticketsbought){
    double price = 0;
    if (isValid(ticketsbought)){
      this.availableTickets = availableTickets - ticketsbought;
      price = ticketsbought * ticketPrice;
    }
    return price;
  }
}
