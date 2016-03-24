package ViewUI;

import Controller.Controller;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.List.IndexOutOfRangeException;
import Repository.RepoException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

import java.io.IOException;

/**
 * Created by Patri on 1/19/2016.
 */
public class OneStepCtrl {
    private Controller ctrl;
    public TextArea preview;

    public void init(Controller ctrl) throws InterruptedException, NotKeyException, IndexOutOfRangeException, RepoException, IOException {
        this.ctrl = ctrl;
        this.ctrl.oneStep();
        preview.appendText(ctrl.getPrgList().toString());
    }

    public void actionBack(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void actionOneStep(ActionEvent event) throws InterruptedException, NotKeyException, IndexOutOfRangeException, RepoException, IOException {
        this.ctrl.oneStep();
        this.preview.setText(ctrl.getPrgList().toString());
    }


}
