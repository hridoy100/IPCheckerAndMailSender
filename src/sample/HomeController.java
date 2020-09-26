package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HomeController {
    private Main main;

    @FXML
    private TextField from;

    @FXML
    private TextField to;

    @FXML
    private TextField password;

    @FXML
    private TextField sleepInterval;


    void setMain(Main main) {
        this.main = main;
        if(main.from!=null && main.from.length()>0){
            from.setText(main.from);
        }
        if(main.to!=null && main.to.length()>0){
            to.setText(main.to);
        }
        if(main.password!=null && main.password.length()>0){
            password.setText(main.password);
        }
    }

    @FXML
    void sendTestMail(ActionEvent event) throws IOException {
        try {
            if(from.getText().length()>0 && to.getText().length()>0 && password.getText().length()>0) {
                main.from = from.getText().toLowerCase();
                main.to = to.getText().toLowerCase();
                main.password = password.getText().toLowerCase();
                main.showMailSentPage();
            }
        } catch (IOException e) {
            main.showExceptionPage(e);
        }
    }

    @FXML
    void startApplication(ActionEvent event) throws IOException {
        try {
            if(from.getText().length()>0 && to.getText().length()>0 && password.getText().length()>0 && sleepInterval.getText().length()>0) {
                main.from = from.getText().toLowerCase();
                main.to = to.getText().toLowerCase();
                main.password = password.getText();
                main.interval = sleepInterval.getText();
                main.showStartedApplicationPage();
            }
        } catch (IOException e) {
            main.showExceptionPage(e);
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
