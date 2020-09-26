package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main extends Application {
    Stage mainStage;
    FXMLLoader loader;

    public String from, to, password, interval;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        showHomePage();
    }

    public void showHomePage() throws IOException {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        mainStage.setTitle("Home Page");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }


    public void showStartedApplicationPage() throws IOException {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("application_started.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ApplicationController controller = loader.getController();
        controller.setMain(this);
        controller.init(from.toLowerCase(), to.toLowerCase(), password, interval);

        controller.startApplication();

        // Set the primary stage
        mainStage.setTitle("Application Running");
        mainStage.setScene(new Scene(root));
        mainStage.setOnHidden(e -> controller.shutdown());
        mainStage.show();
    }

    public void showMailSentPage() throws IOException {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mail_sent.fxml"));
        Parent root = loader.load();

        // Loading the controller
        MailSentController controller = loader.getController();
        controller.setMain(this);
        controller.sendMail(from.toLowerCase(), to.toLowerCase(), password);

        // Set the primary stage
        mainStage.setTitle("Mail Sent Confirmation Page");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    public void showExceptionPage(Exception e) throws IOException {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("exception.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ExceptionController controller = loader.getController();
        controller.setMain(this);
        controller.setMessage(e);

        // Set the primary stage
        mainStage.setTitle("Exception Occurred Page");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    public void showDeveloper(){
        try {
            Desktop.getDesktop().browse(new URI("http://facebook.com/hridoy100"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    public void showHelp(){
        try {
            Desktop.getDesktop().browse(new URI("https://medium.com/graymatrix/using-gmail-smtp-server-to-send-email-in-laravel-91c0800f9662/#4ced"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
