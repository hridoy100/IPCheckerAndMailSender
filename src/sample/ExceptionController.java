package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ExceptionController {
    @FXML
    private Label exception;

    private Main main;

    void setMain(Main main) {
        this.main = main;
    }
    public void setMessage(Exception e){
        exception.setText(e.getMessage());
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
