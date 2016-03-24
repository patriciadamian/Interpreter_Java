package ViewUI;/**
 * Created by Patri on 1/19/2016.
 */

import Controller.Controller;
import Domain.ProgramState.PrgState;
import Repository.IRepository;
import Repository.Repository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {

    private Stage window;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        List<PrgState> programs = new ArrayList<>();
        ctrl.getRepo().setPrgList(programs);

        primaryStage.setTitle("Toy Language Interpreter");
        window = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(InitialMenuCtrl.class.getResource("InitialMenu.fxml"));
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(InitialMenuCtrl.class.getResource("InitialMenu.fxml").openStream());

        InitialMenuCtrl ctrl2 = fxmlLoader.getController();
        ctrl2.init(ctrl);

        Scene scene = new Scene(root);
        window.setScene(scene);

        window.show();
    }

}
