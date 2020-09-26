package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MailSentController {
    private Main main;

    @FXML
    private Label confirmationMessage;
    @FXML
    private Label currentIP;


    void setMain(Main main) {
        this.main = main;
    }

    public void sendMail(String from, String to, String password){
        String currentIpAddess = IPAddressChecker.getCurrentIPAddress();
        if(!currentIpAddess.equals("false")) {
            currentIP.setText(currentIpAddess);
        }
        else {
            currentIP.setText("No internet! Please connect with internet!");
        }
        if(SendMailSSL.send(from,password,to,"Test Mail From IP Checking App","This is an auto generated testing mail sent to you by the IP Checker App.")){
            confirmationMessage.setText("Mail Sent Successfully!");
        }
        else {
            confirmationMessage.setText("Mail Sending Failed!");
        }
    }

    @FXML
    void back(ActionEvent event){
        try {
            main.showHomePage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void helpClicked(ActionEvent event) throws IOException {
//        loopCondition = false;
        main.showHelp();
    }

    @FXML
    void developerClicked(ActionEvent event) throws IOException {
//        loopCondition = false;
        main.showDeveloper();
    }
}
