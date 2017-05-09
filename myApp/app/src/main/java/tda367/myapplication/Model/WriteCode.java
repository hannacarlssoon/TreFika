package tda367.myapplication.Model;

import tda367.myapplication.Server;

/**
 * Created by sarakitzing on 2017-05-08.
 */

public class WriteCode {
    private final String question;
    private final String answer;
    private String userAnswer;
    private String compUserAnswer;



    public WriteCode (String question, String answer){
        this.question = question;
        this.answer= answer;
    }

    public boolean checkAnswer(String userAnswer){
        compileCode(userAnswer);
        System.out.println("Correct: " + answer);
        System.out.println("compUser: " + compUserAnswer);
        System.out.println("Error: " + compUserAnswer.equals("Error"));
        System.out.println("Wrong answer: " + compUserAnswer.equals(answer));
        if(compUserAnswer.equals("Error")){
            return false;
        } else if (compUserAnswer.equals(answer)){
            return false;
        }
        return true;
    }

    public void compileCode(String userCode){
        StringBuilder sb = new StringBuilder();
        sb.append(userCode);

        Server server = new Server("127.0.0.1", sb.toString());
        server.startRunning();
        compUserAnswer = server.getCompiledCode();
    }


}
