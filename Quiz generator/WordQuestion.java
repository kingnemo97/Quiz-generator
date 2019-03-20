import java.util.Scanner;
public class WordQuestion extends QuizQuestion
{
   public Scanner kb = new Scanner(System.in);
   private boolean caseSensitive = false;

   public WordQuestion(String question, String answer, boolean caseSensitive)
   {
      super(question, answer);
      this.caseSensitive = caseSensitive;
   }
   
   public static WordQuestion readQuestion(Scanner in)
   {
      String question = in.nextLine();
      String answer = in.nextLine();
      String subAnswer;
      boolean temp = false;
      if(answer.contains("/"))
      {
         int a = answer.indexOf('/');
         subAnswer = answer.substring(2,a);
         temp = true;
      }
      else
      {
         subAnswer = answer.substring(2);
      }
      return (new WordQuestion(question, subAnswer, temp));

   }
   
   public WordAnswer administer()
   {
      System.out.print(this);
      String userAnswer = kb.nextLine();

      return new WordAnswer(this, userAnswer);
   }

   public boolean getCaseSensitive()
   {
      return caseSensitive;
   }

   public String toString()
   {
      return (super.getQuestion() + "\nType your answer: ");
   }
}
