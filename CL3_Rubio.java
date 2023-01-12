import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class CL3_Rubio{
  public static void main(String [] args)throws IOException{
    Scanner userInput = new Scanner(System.in);
    FileReader fileReader = new FileReader();
    fileReader.setFileName("events-info.csv");
    Event [] eventList = fileReader.readFile();

    boolean shopping = true;
    boolean questionTwo = true; // second question that its asked

    System.out.println("------------------------------------------------------------");
    System.out.println("Welcome to Paydirt Tickets! What can we help you with today?");

    while(shopping){
      System.out.println("------------------------ MAIN MENU -------------------------");
      System.out.println("1. View events" + "\n2. View concessions" +
      "\n3. Purchase tickets and concessions" + "\n4. EXIT system");

      int option = userInput.nextInt();

      switch(option){
        case 1:
          viewAllEventNames(eventList);

          break;
        case 2:
          viewAllConcessions(eventList);

          break;
        case 3:
          viewAllEventNames(eventList);
          System.out.println("\nEnter the number of event you'd like to attend.");
          System.out.print(">>");

          int eventNum = userInput.nextInt();
          
          System.out.println("\n" + "You selected: " + eventList[eventNum-1].getEventName() +
          "\n" + "Tickets for " + eventList[eventNum-1].getEventName() + " cost $" +
          eventList[eventNum-1].getTicketPrice() + "\n" + "How many tickets would you like to buy?");
          System.out.print(">>");

          int numOfTickets = userInput.nextInt();

          System.out.println("\nYour purchase was successful!" + "\nYou will be charged $" +
          String.format("%.2f", eventList[eventNum-1].purchaseTickets(numOfTickets)) +
          " for tickets. There are " + eventList[eventNum-1].getAvailableTickets() + " left.");

          float totalTicketsCharged = 0f; // Keeps track of purchase items

          totalTicketsCharged += eventList[eventNum-1].purchaseTickets(numOfTickets);

          float totalConcessionCharge = 0f;
          float concessionCharge = 0f;
          String concessionFood = "";

          System.out.println("\nWould you like go buy concessions? (y/n)");
          System.out.print(">>");

          char question = userInput.next().charAt(0);

          while (questionTwo) {
            switch (question) {
              case 'y':
                System.out.println("\nHere are all the concessions at " + eventList[eventNum-1].getEventName()
                 +"\n" + eventList[eventNum-1].getConcessionStand() );
                System.out.println("\nWhich concession would you like to to purchase? [enter name of concession]");

                concessionFood = userInput.nextLine();//flush

                boolean chechConcessionInput = true;

                while(chechConcessionInput){
                  System.out.print(">>");
                  concessionFood = userInput.nextLine();

                  if (concessionFood.equalsIgnoreCase("popcorn")){
                    concessionCharge += eventList[eventNum-1].getConcessionStand().getPopcornPrice();
                    chechConcessionInput = false;
                  }
                  else if (concessionFood.equalsIgnoreCase("soda")){
                    concessionCharge += eventList[eventNum-1].getConcessionStand().getSodaPrice();
                    chechConcessionInput = false;
                  }
                  else if (concessionFood.equalsIgnoreCase("hot dog")){
                    concessionCharge += eventList[eventNum-1].getConcessionStand().getHotDogPrice();
                    chechConcessionInput = false;
                  }
                  else {
                    System.out.println("Invalid user input");
                  }
                }
                System.out.println("\nHow many of these items would you like to buy?");

                int itemsPurchace = userInput.nextInt();
                totalConcessionCharge += concessionCharge * itemsPurchace;
                /* ^ keeps track of all concessions*/

                System.out.println("\nWould you like to buy any more concessions? (y/n)");
                System.out.print(">>");

                question = userInput.next().charAt(0);

                if (question == 'n'){
                  questionTwo = false;
                }
                break;
              case 'n':
                questionTwo = false;
                break;
              default:
                System.out.println("Invalid userInput");
                break;
            }
          }
          float total = totalTicketsCharged + totalConcessionCharge;

          System.out.println("\nTicket charge: $" + String.format("%.2f", totalTicketsCharged) + "\nConession charge: $"
          + String.format("%.2f", totalConcessionCharge) + "\nTotal: $" + String.format("%.2f", total) );
          System.out.println("\nPleace enter a 16 digit credit card number to complete purchase:");

          boolean checkPurchase = true;

          while(checkPurchase){
            System.out.print(">>");
            long creditCard = userInput.nextLong();
            if(isCreditCardNumberValid(creditCard)){
              System.out.println();
              System.out.print("Your card ending in ");
              lastFour(creditCard);
               System.out.println(" was charged $" + String.format("%.2f", total)
               + "\nThank you for your purchase and for using PaydirtTickets!" + "\nGoodbye!");
              checkPurchase = false;
              shopping = false;
            }
            else {
              System.out.println("Error please try again");
            }
          }

          break;
        case 4:
          shopping = false;

          break;
        default:
          System.out.println("Invalid user input");

          break;
      }
    }
  }

  public static void viewAllEventNames(Event [] event){
    System.out.println("All events:" + "\n");
    int numOfEvents = 1;
    for(int i = 0; i < event.length-1; i++){
      System.out.println("       Event " + numOfEvents + "\nEVENT NAME: " + event[i].getEventName()
      + "\nVENUE NAME: " + event[i].getVenueName() + "\nADDRESS: " + event[i].getAddress()
      + "\nTICKET PRICE: $" + event[i].getTicketPrice() + "\nAVAILABLE TICKETS: " + event[i].getAvailableTickets()
      +"\n");
      numOfEvents++;
    }
  }

  public static void viewAllConcessions(Event [] concessions){
    System.out.println("All concessions:" + "\n");
    for(int i = 0; i < concessions.length-1;i++){
      System.out.println(concessions[i].getEventName() + "\n" + concessions[i].getConcessionStand() +"\n" );
    }
  }

  public static boolean isCreditCardNumberValid(long creditCard){
    int creditCardLength = String.valueOf(creditCard).length();
    if (creditCardLength == 16){
      return true;
    }
    else {
      return false;
    }
  }

  public static void lastFour(long creditCard){
    System.out.print(Long.toString(creditCard).substring(Long.toString(creditCard).length()-4));
  }
}
