package ViewUI;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextArea;


import java.io.IOException;

/**
 * Created by Patri on 1/19/2016.
 */
public class DeserializeCtrl {
    private Controller ctrl;
    public TextArea preview;

    public void init(Controller ctrl) throws IOException {
        this.ctrl = ctrl;
        this.ctrl.getRepo().deserializePrg();
        preview.appendText(ctrl.getRepo().getPrgList().toString());
    }

    public void actionBack(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
