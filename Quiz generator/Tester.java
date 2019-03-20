import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.io.File;
import java.lang.IndexOutOfBoundsException;
public class Tester
{
   private static int numElements = 0;
   private static int completed = 0;

   public static void main (String [] args)
   {
      Scanner kb = new Scanner(System.in);
      Scanner in = null;   
      ArrayList<QuizQuestion> list = new ArrayList<QuizQuestion>();
      ArrayList<QuizAnswer> ans = new ArrayList<QuizAnswer>();
	  
	
      //int numElements = 0;
      char option = 'D';
      while(option != 'Q')
      {
         System.out.println("\n--------" +
               "\nOptions:" +
               "\n--------" +
               "\nR: Read question details from a text file" +
               "\nA: Administer the quiz test" +
               "\nF: Adminster the test with the option to quit" +
               "\nD: Display the answers on the screen" +
			   "\nW: Write to a file" +
               "\nQ: Quit \n");
		 System.out.print("Enter choice: ");
         try
         {
         option = kb.nextLine().toUpperCase().charAt(0);
         }
         catch(IndexOutOfBoundsException e)
         {
            option = 'b';
         }

         switch(option)
         {
            case 'R':ReadQuestion(list, kb, in);
                     break;
            case 'A':Administer(list, ans, numElements);
                     break;
            case 'F':AdministerQuit(list, ans, kb, numElements);
                     break;
            case 'D':Display(list, ans);
                     break;
            case 'W':WriteFile(list, ans, kb);
                     break;
            case 'Q':break;
            default: System.out.println("That is not a valid input (all inputs are case sensitive)");
         }
      }
   }

   public static void ReadQuestion(ArrayList<QuizQuestion> list, Scanner kb, Scanner in)
   {
      int tries = 0;
      boolean file = false;
      while(!file||tries != 5)
      {
         tries++;
         try
         {
            System.out.println(tries);
            System.out.print("Enter the a file name: ");
            String fileName = kb.nextLine();
            in = new Scanner(new File(fileName));
            file = true;
         }
         catch(FileNotFoundException e)
         {
            System.out.println("File not found, try again.");
         }
      }

      while(in.hasNext())
      {
         char a = in.nextLine().charAt(0);

         if(a == 'T')
         {
            list.add(TrueFalseQuestion.readQuestion(in));
            in.nextLine();
         }
         else if(a == 'W')
         {
            list.add(WordQuestion.readQuestion(in));
            in.nextLine();
         }
         else
         {
            list.add(MultipleChoiceQuestion.readQuestion(in));
         }
         numElements++;
      }
   }

   public static void Administer(ArrayList<QuizQuestion> list, ArrayList<QuizAnswer> ans, int numElements)
   { 
      for(int x = ans.size(); x < numElements; x++)
      {
         System.out.println("QUESTION " + (x + 1));
         ans.add(list.get(x).administer());
		 System.out.println();
		 completed++;
      }
   }

   public static void AdministerQuit(ArrayList<QuizQuestion> list, ArrayList<QuizAnswer> ans, Scanner kb, int numElements)
   {
      for(int x = ans.size(); x<numElements; x++)
      {
         System.out.println("QUESTION " + (x + 1));
         ans.add(list.get(x).administer());
         System.out.println();
         System.out.println("Do you want to continue? (y/n): ");
         char a = kb.nextLine().charAt(0);
         if(a == 'n')
         {
            break;
         }
      }
   }

   public static void Display(ArrayList<QuizQuestion> list, ArrayList<QuizAnswer> ans)
   {
      int correct = 0;
      int incorrect = 0;
      for(int x = 0; x < ans.size(); x++)
      {
         System.out.println("QUESTION " + (x + 1) + 
                            "\n" + list.get(x).getQuestion());
         list.get(x).getList();
         System.out.println("You're answer to the question was " + ans.get(x).getUserAnswer());
         if(ans.get(x).getResult() == 'i')
         {
            System.out.println("It is incorrect");
            System.out.println("The correct answer is: " + list.get(x).getCorrectAnswer());
            System.out.println("...");
            System.out.println();
            incorrect++;
         }
         else
         {
            System.out.println("It is correct");
            System.out.println();
            correct++;
         }
         }
         System.out.println("Number of questions: " + list.size() +
                           "\n Number of answers: " + ans.size() +
                           "\n Number of correct answers: " + correct +
                           "\n Number of incorrect answers: " + incorrect);
      
   }

   public static void WriteFile(ArrayList<QuizQuestion> list, ArrayList<QuizAnswer> ans , Scanner kb)
   {
      int correct = 0;
      int incorrect = 0;
      try
      {
         System.out.print("Enter a file name: (any existing file will be deleted ");
         String file = kb.nextLine();
         PrintWriter w = new PrintWriter(new File(file));
		 for(int x = 0; x<ans.size(); x++)
		 {
			w.println("QUESTION " + (x + 1) + 
			"\n" + list.get(x).getQuestion());
			w.println("You're answer to the question was " + ans.get(x).getUserAnswer());
			if(ans.get(x).getResult() == 'i')
			{
				 w.println("It is incorrect");
				 w.println("The correct answer is: " + list.get(x).getCorrectAnswer());
				 w.println("...");
			}
			else
			{
			w.println("It is correct");
			w.println();
		   }
       }
         w.println("Number of questions: " + list.size() +
                           "\n Number of answers: " + ans.size() +
                           "\n Number of correct answers: " + correct +
                           "\n Number of incorrect answers: " + incorrect);
		  w.close();
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      
   }
}
