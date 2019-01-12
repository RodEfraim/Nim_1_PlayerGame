import java.util.Random;
import java.util.Scanner;

/**
 *  One-player game of Nim. User vs the computer. This game of Nim contains 3
 *  towers. The last player to make the final move looses.
 */
public class autoNim
{
  public static void main(String[] args)
  {
    Random r = new Random();
    Scanner keyboardString = new Scanner(System.in);

    int countA = 3;
    int countB = 4;
    int countC = 5;

    int randomPile;
    int randomSubtract = 0;
    boolean randomPileSelection = false;

    int subtractNum = 0;

    boolean playerTurn = false;
    boolean playerCurrentWins = false;

    boolean pileNameError = false;
    boolean pileZeroError = false;
    boolean subtractError = false;

    String player1;
    String player2; 
    String playerCurrent;
    String playerNext;
    String pileName;

    System.out.print("Player 1, enter your name: ");
    player1 = keyboardString.nextLine();
    playerCurrent = player1;

    //System.out.print("Player 2, enter your name: ");
    player2 = "COMPUTER";
    playerNext = "COMPUTER";

    visualDisplay(countA, countB, countC);

    while(countA != 0 || countB != 0 || countC != 0)
    {
      if(playerTurn == false)
      {
        playerCurrent = player1;
        playerNext = player2;
      }
      else
      {
        playerCurrent = player2;
        playerNext = player1;
      }
    
      if(playerTurn == false)
      { 
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~HUMAN PILE INPUT~~~~~~~~~~~~~~~~~~~~~~~~~~
        do
        {
          pileNameError = false;
          pileZeroError = false;

          System.out.print("\n" + playerCurrent + ", choose a pile: ");

          pileName = keyboardString.nextLine();

          if(pileName.equals("A") || pileName.equals("a"))
          {
            if(countA == 0)
              pileZeroError = true;
          }
          else if(pileName.equals("B") || pileName.equals("b"))
          {
            if(countB == 0)
              pileZeroError = true;
          }
          else if(pileName.equals("C") || pileName.equals("c"))
          {
            if(countC == 0)
              pileZeroError = true;
          }
          else
          {
            pileNameError = true;  
            System.out.println("INCORRECT PILE NAME. Please try again.");
          }

          if(pileZeroError == true)
            System.out.println("Nice try, " + playerCurrent + ". That pile is empty.");
          
        }while(pileNameError || pileZeroError);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~HUMAN subtractNum INPUT~~~~~~~~~~~~~~~~~~~~~~~~~~
        System.out.print("How many to remove from pile " + pileName + ": ");
        do
        {
          subtractError = false;

          //Prevents string input.
          try
          {  
            Scanner keyboardInteger = new Scanner(System.in);
            subtractNum = keyboardInteger.nextInt();
          }
          catch(Exception e)
          {
            System.out.print("Exception Occured. Please enter an integer.");
            subtractError = true;
            continue;
          }

          if(subtractNum <= 0)
          {
            System.out.print("You must remove at least 1. Try again, how many? ");
            subtractError = true;
            //The continue on do-while makes a jump to the bottom, so the program
            //can evaluate if has to continue with another iteration or exit. In
            //this case it will continue because subtractError is set to true.
            continue;
          }
          
          if(pileName.equals("A") || pileName.equals("a"))
          {
            if(subtractNum > countA)
            {
              System.out.print("Pile A doesn't have that many. Try again: ");  
              subtractError = true;
            }
            else
              countA = countA - subtractNum;
          }
          else if(pileName.equals("B") || pileName.equals("b"))
          {
            if(subtractNum > countB)
            {
              System.out.print("Pile B doesn't have that many. Try again: ");  
              subtractError = true;
            }
            else
              countB = countB - subtractNum;
          }
          else if(pileName.equals("C") || pileName.equals("c"))
          {
            if(subtractNum > countC)
            {
              System.out.print("Pile C doesn't have that many. Try again: ");  
              subtractError = true;
            }
            else
              countC = countC - subtractNum;
          }
        }while(subtractError);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //The ELSE STATEMENT is the computer player's turn.
      }
      else
      {
        System.out.print("\n" + playerCurrent + ", choose a pile: ");
        do
        {
          randomPileSelection = true;
          pileName = "";

          randomPile = 1 + r.nextInt(3);

          if(randomPile == 1 && countA != 0)
          {
            pileName = "A";
            randomPileSelection = false;
          }
          else if(randomPile == 2 && countB != 0)
          {
            pileName = "B";
            randomPileSelection = false;
          }
          else if(randomPile == 3 && countC != 0)
          {
            pileName = "C";
            randomPileSelection = false;
          }
        }while(randomPileSelection); 

        System.out.print(pileName);
        System.out.print("\nHow many to remove from pile " + pileName + ": ");

        if(pileName.equals("A"))
        {
          if(countB == 0 && countC == 0)
            randomSubtract = countA - 1;
          else 
            randomSubtract = 1 + r.nextInt(countA);

          countA = countA - randomSubtract;
        }
        else if(pileName.equals("B"))
        {
          if(countA == 0 && countC == 0)
            randomSubtract = countB - 1;
          else
            randomSubtract = 1 + r.nextInt(countB);

          countB = countB - randomSubtract;
        }
        else if(pileName.equals("C"))
        {
          if(countA == 0 && countB == 0)
            randomSubtract = countC - 1; 
          else
            randomSubtract = 1 + r.nextInt(countC);

          countC = countC - randomSubtract;
        }
        System.out.println(randomSubtract);
      }

      visualDisplay(countA, countB, countC);
    
      //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~END GAME CONDITIONS~~~~~~~~~~~~~~~~~~~~~~~~~~
      if(countA == 1 && countB == 0 && countC == 0)
      {
        playerCurrentWins = true;
        countA = 0;
      }
      else if(countA == 0 && countB == 1 && countC == 0)
      {
        playerCurrentWins = true;
        countB = 0;
      }
      else if(countA == 0 && countB == 0 && countC == 1)
      {
        playerCurrentWins = true;
        countC = 0;
      }
      //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

      //visualDisplay(countA, countB, countC);
      playerTurn = !playerTurn;
    }

    if(playerCurrentWins)
      System.out.println(playerNext + ", you must take the last remaining counter, so you loose. " + playerCurrent + " wins!");
    else
    {
      //This is the case when a player removes all elements of the last standing pile! Reckless, stupid move.
      System.out.println(playerCurrent + ", you have made the last move, so you loose, " + playerNext + " wins!");
      System.out.println(playerCurrent + ", this was a STUPID move!");
    }
   
    System.out.println("GAME OVER");
  }
  
  /**
   *  Displays the current status of the game. Displays the visual
   *  representation of the 3 towers.
   *
   *  @param countA: The number of stacks remaining in tower A.
   *  @param countB: The number of stacks remaining in tower B.
   *  @param countC: The number of stacks remaining in tower C.
   */
  public static void visualDisplay(int countA, int countB, int countC)
  {
    int countDisplay;
    int largestPile;

    System.out.println("\nA: " + countA + "\tB: " + countB + "\tC: " + countC + "\n");
      
    if(countA <= countB)
      largestPile = countB;
    else
      largestPile = countA;

    if(largestPile <= countC)
      largestPile = countC;

    for(countDisplay = largestPile; countDisplay > 0; countDisplay--)
    {
      if(countDisplay <= countA)
        System.out.print("\t* ");  
      else
        System.out.print("\t  ");

      if(countDisplay <= countB)
        System.out.print("* ");
      else
        System.out.print("  ");

      if(countDisplay <= countC)
        System.out.print("* ");
      else
        System.out.print("  ");

      System.out.println();
    }
    
    System.out.println("\tA B C");
  }
}
