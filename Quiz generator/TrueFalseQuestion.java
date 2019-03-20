import java.util.Scanner;
import java.io.*;
public class TrueFalseQuestion extends QuizQuestion
{
   public Scanner kb = new Scanner(System.in);

   public TrueFalseQuestion(String question, String answer)
   {
      super(question, answer);
   }
   
   public static TrueFalseQuestion readQuestion(Scanner in)
   {
     String question = in.nextLine();
     String answer = in.nextLine();
     String subAnswer = answer.substring(2);
     System.out.println(subAnswer);
     return (new TrueFalseQuestion(question, subAnswer));
   }

   public TrueFalseAnswer administer()
   {
      String userAnswer = "";
      boolean validAnswer = false;
      while(!validAnswer)
      {
      System.out.print(this);
      userAnswer = kb.nextLine();
      if(userAnswer.equalsIgnoreCase("true")||userAnswer.equalsIgnoreCase("false"))
      {
         validAnswer = true;
      }
      else
      {
         System.out.println("You must enter 'true' of 'false'");
      }
      }
      return (new TrueFalseAnswer(this, userAnswer));
   }

   public String toString()
   {
      return (super.getQuestion() + "\nType true or false: ");
   }
}
