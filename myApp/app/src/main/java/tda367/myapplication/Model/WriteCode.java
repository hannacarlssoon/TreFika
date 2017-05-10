package tda367.myapplication.model;

import tda367.myapplication.service.Server;

/**
 * Created by sarakitzing on 2017-05-08.
 */

//Handles the logic behind the WriteCode query such as checking the answer and creating the question
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
        return compUserAnswer.equals(answer);

    }

    //TODO change - model shouldn't call server, controller should
    public void compileCode(String userCode){
        StringBuilder sb = new StringBuilder();
        sb.append(userCode);

        Server server = new Server("127.0.0.1", sb.toString());
        server.startRunning();
        compUserAnswer = server.getCompiledCode();
    }


}
