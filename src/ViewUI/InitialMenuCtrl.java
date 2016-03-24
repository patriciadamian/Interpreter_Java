package ViewUI;

import Controller.Controller;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IndexOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.DataStructures.Stack.EmptyStackException;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.VarNotDefinedException;
import Repository.RepoException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;

/**
 * Created by Patri on 1/19/2016.
 */
public class InitialMenuCtrl {
    private Controller ctrl;
    public CheckBox checkLog;


    public void init(Controller ctrl){
        this.ctrl = ctrl;
        checkLog.setSelected(ctrl.isLogFlag());
    }

    public void actionInputProgram(ActionEvent event) {
        Stage primaryStage = new Stage();
        URL location = InputProgramCtrl.class.getResource("InputProgram.fxml");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load(location.openStream());

            InputProgramCtrl ctrl2 = fxmlLoader.getController();
            ctrl2.init(ctrl);

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        primaryStage.show();

    }

    public void actionOneStep(ActionEvent event) throws IOException, InterruptedException, NotKeyException, RepoException, IndexOutOfRangeException {
        Stage primaryStage = new Stage();
        URL location = OneStepCtrl.class.getResource("OneStep.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

        Parent root = fxmlLoader.load(location.openStream());

        OneStepCtrl ctrl2 = fxmlLoader.getController();
        ctrl2.init(ctrl);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void actionAllStep(ActionEvent event) throws IOException, EmptyStackException, IsFullListException, IndexOutOfRangeException, IsFullDictException, HeapOutOfRangeException, NotKeyException, DivisionByZeroException, VarNotDefinedException, RepoException, InterruptedException {
        Stage primaryStage = new Stage();
        URL location = AllStepCtrl.class.getResource("AllStep.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

        Parent root = fxmlLoader.load(location.openStream());

        AllStepCtrl ctrl2 = fxmlLoader.getController();
        ctrl2.init(ctrl);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void actionSerialize(ActionEvent event){
        ctrl.getRepo().serializePrg();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Serialized");
        alert.setContentText("Serialization complete !");

        alert.showAndWait();
    }

    public void actionDeserialize(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        URL location = DeserializeCtrl.class.getResource("Deserialize.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

        Parent root = fxmlLoader.load(location.openStream());

        DeserializeCtrl ctrl2 = fxmlLoader.getController();
        ctrl2.init(ctrl);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void actionCheckLog(ActionEvent event){
        ctrl.changeLogFlag();
    }

    public void actionExit(ActionEvent event){
        Platform.exit();
    }

}
