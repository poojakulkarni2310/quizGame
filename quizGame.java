import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class quizGame {

    private static Map<String,Quiz> quizzes = new HashMap<>();

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter command (create, take, view, list, exit) : ");
            String command = sc.next();
            
            if(command.equalsIgnoreCase("create"))
            {
                createQuiz(sc);
            }
            else if(command.equalsIgnoreCase("take"))
            {
                takeQuiz(sc);
            }
            else if(command.equalsIgnoreCase("view"))
            {
                viewQuiz(sc);
            }
            else if(command.equalsIgnoreCase("list"))
            {
                listQuiz();
            }
            else if(command.equalsIgnoreCase("exit"))
            {
                break;
            }
            else
            {
                System.out.println("Invalid command...!");
            }
        }
    }

    private static void createQuiz(Scanner sc)
    {
        System.out.print("Enter the Name of the Quiz  :  ");
        String quizname = sc.next();

        Quiz quiz = new Quiz(quizname);
        System.out.print("Enter Number of Questions  :  ");
        int numQ = sc.nextInt();

        for(int i=0; i<numQ; i++)
        {
            System.out.print("Enter the Question  :  ");
            String question = sc.next();

            System.out.println("Enter Number of Choices  :  ");
            int numChoices = sc.nextInt();

            List<String> choices = new ArrayList<>();
            for(int j=0;j<numChoices;j++)
            {
                System.out.print("Enter Choice "+(j+1)+" : ");
                String choice = sc.next();
                choices.add(choice);
            }
            System.out.print("Enter the index of the current choice : ");
            int currentChoice = sc.nextInt()-1;

            quiz.addQuestion(new Question(question,choices,currentChoice));

        }

        quizzes.put(quizname,quiz);
        System.out.println("Quiz Created..");
    }

    private static void takeQuiz(Scanner sc)
    {
        System.out.print("Enter the Name of the Quiz : ");
        String quizName = sc.next();

        Quiz quiz = quizzes.get(quizName);
        if(quiz == null)
        {
            System.out.print("Quiz not found...!");
            return;
        }

        int score = 0;
        for(int i=0;i<quiz.getNumQuestions();i++)
        {
            Question question = quiz.getQuestions(i);

            System.out.println("Question "+(i+1)+" : "+ question.getQuestions());
            List<String> choices = question.getChoices();

            for(int j=0;j<choices.size();j++)
            {
                System.out.println((j+1)+" : "+choices.get(j));
            }
            System.out.print("Enter your answer : ");
            int userAns = sc.nextInt();

            if(userAns == question.getCorrectChoice())
            {
                System.out.println("Correct!");
                score++;
            }
            else
            {
                System.out.println("Incorrect...The correct answer is "+ (question.getCorrectChoice()+1)+".");
            }
        }
        System.out.println("Your score is : "+score+" out of "+quiz.getNumQuestions()+".");
    }

    private static void viewQuiz(Scanner sc)
    {
        System.out.print("Enter the name of the quiz : ");
        String quizName = sc.nextLine();

        Quiz quiz = quizzes.get(quizName);
        if(quiz == null)
        {
            System.out.println("Quiz not found...!");
            return;
        }

        System.out.println("Quiz : "+quiz.getName());

        for(int i=0;i<quiz.getNumQuestions();i++)
        {
            Question question = quiz.getQuestions(i);
            System.out.println("Question "+(i+1)+" : "+question.getQuestions());
            List<String> choices = question.getChoices();

            for(int j=0;j<choices.size();j++)
            {
                System.out.println((j+1)+" : "+choices.get(j));
            }
            System.out.println("Answer : "+(question.getCorrectChoice()+1));
        }
    }

    private static void listQuiz()
    {
        System.out.println("Quizzesss......");
        for(String quizName : quizzes.keySet())
        {
            System.out.println("- "+quizName);
        }
    } 
}

class Quiz 
{
    private String name;
    private List<Question> questions = new ArrayList<>();

    public Quiz(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    public void addQuestion(Question question)
    {
        questions.add(question);
    }

    public Question getQuestions(int index)
    {
        return questions.get(index);
    }

    public int getNumQuestions()
    {
        return questions.size();
    }

}

class Question
{
    private String question;
    private List<String> choices;
    private int correctChoice;

    public Question(String question,List<String> choices,int correctChoice)
    {
        this.question=question;
        this.choices=choices;
        this.correctChoice=correctChoice;
    }

    public String getQuestions()
    {
        return question;
    }

    public List<String> getChoices()
    {
        return choices;
    }

    public int getCorrectChoice()
    {
        return correctChoice;
    }
}
