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

    public boolean checkAnswer(){
        compileCode(userAnswer);
        if(userAnswer.equals("Error")){
            return false;
        } else if (!userAnswer.equals(answer)){
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
