public class QuizAnswer
{
   private QuizQuestion quizquestion;
   private String userAnswer;
   private char result;

   public QuizAnswer(QuizQuestion quizquestion, String userAnswer)
   {
      this.quizquestion = quizquestion;
      this.userAnswer = userAnswer;
      checkQuestion();

   }

   public void checkQuestion()
   {
      
      if(quizquestion instanceof WordQuestion)
      {
         boolean caseSensitive = ((WordQuestion)quizquestion).getCaseSensitive();
         if(caseSensitive == true)
         {
            if(quizquestion.getCorrectAnswer().equals(userAnswer))
            {
               result = 'c';
            }
            else
            {
               result = 'i';
            }
         }
         else
         {
            if(quizquestion.getCorrectAnswer().equalsIgnoreCase(userAnswer))
            {
               result = 'c';
            }
            else
            {
               result = 'i';
            }
         }
      }
      else
      {
      
         if(quizquestion.getCorrectAnswer().equalsIgnoreCase(userAnswer))
         {
            result = 'c';
         }
         else
         {
            result = 'i';
         }
      }
   }

   public String getUserAnswer()
   {
      return userAnswer;
   }
   public char getResult()
   {
      return result;
   }
}
