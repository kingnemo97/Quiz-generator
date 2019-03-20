import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class MultipleChoiceQuestion extends QuizQuestion
{
   public Scanner kb = new Scanner(System.in);
   private ArrayList<String> list = new ArrayList<>();
   private int numChoices;

   public MultipleChoiceQuestion(String question, String answer,int numChoices, ArrayList<String> list)
   {
      super(question, answer);
      this.numChoices = numChoices;
      this.list = list;
   }

   public static MultipleChoiceQuestion readQuestion(Scanner in)
   {
      String question = in.nextLine();
      String answer = "";
      int temp = 0;
      ArrayList<String> whatever = new ArrayList<>();
      char a = 'a';
      char c = 'a';
      while(in.hasNextLine())
      {
         String varible = in.nextLine();
         if(!(varible.charAt(0) == '.'))
         {
            if(varible.charAt(0) == '+')
            {
               answer += c;
            }
            c++;
         whatever.add(a + " " + varible.substring(2));
         ++temp;
         a++;
         }
         else
         {
            break;
         }
      }

      return (new MultipleChoiceQuestion(question, answer, temp, whatever));
   }

   public MultipleChoiceAnswer administer()
   {
      String userAnswer = "";
      boolean validAnswer = false;
      while(validAnswer == false)
      {
         System.out.println(super.getQuestion());
         for(int x = 0; x<numChoices; x++)
         {
            System.out.println(list.get(x));
         }
         System.out.print("Type in a string of letters (in alphabetical order): ");
         userAnswer = kb.nextLine();
     if(validAnswer(userAnswer) == true)
     { 
         validAnswer = true;
      }
      else
      {
         System.out.println("invalid answer");
      }
      }
      
      return(new MultipleChoiceAnswer(this,userAnswer));
   }

   public void  getList()
   {
      for(int x = 0; x < list.size(); x++)
      {
         System.out.println(list.get(x));
      }
   }
   
   public boolean validAnswer(String userAnswer)
   {
      int t = 0;
     for(int x = 0; x < userAnswer.length(); x++)
      {
         char u = userAnswer.charAt(x);
            if(u >= 'a' && u <= ('a' + numChoices - 1)) 
            {
               t++;
            }
         }
         if(t == userAnswer.length())
         {
            return true;
         }
         else
         {
            return false;
         }
   }

   public String toString()
   {
   return (super.getQuestion());
   }
}
