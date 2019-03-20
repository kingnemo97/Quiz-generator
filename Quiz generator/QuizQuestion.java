import java.util.Scanner;
public abstract class QuizQuestion
{
   private String question;
   private String correctAnswer;

   public QuizQuestion(String question, String correctAnswer)
   {
      this.question = question;
      this.correctAnswer = correctAnswer;
   }

   public abstract QuizAnswer administer();

   public String getQuestion()
   {
      return question;
   }

   public String getCorrectAnswer()
   {
      return correctAnswer;
   }

   public void getList()
   {
   }

   public abstract String toString();
}
