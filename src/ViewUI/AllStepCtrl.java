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
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

import java.io.IOException;

/**
 * Created by Patri on 1/19/2016.
 */
public class AllStepCtrl {
    private Controller ctrl;
    public TextArea preview;

    public void init(Controller ctrl) throws IOException,  IsFullListException, IndexOutOfRangeException, IsFullDictException, HeapOutOfRangeException, NotKeyException, InterruptedException, DivisionByZeroException, VarNotDefinedException, RepoException, EmptyStackException {
        this.ctrl = ctrl;
        this.ctrl.allStep();
        preview.appendText(ctrl.getPrgList().toString());

    }

    public void actionBack(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
