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
    private String compAnswer;



    public WriteCode (String question, String answer){
        this.question = question;
        this.answer= answer;
    }

    public boolean checkAnswer(String userAnswer){
        //compileCode(userAnswer);
        return compAnswer.equals(answer);

    }

/*    public void compileCode(String userCode){
        StringBuilder sb = new StringBuilder();
        sb.append(userCode);
        sb.toString();

        Server server = new Server("127.0.0.1");
        server.setUserCode(userCode);
        server.startRunning();
        compAnswer = server.getCompiledCode();
    }
*/

    public void setCompAnswer (String compAnswer){
        this.compAnswer = compAnswer;
    }


}
