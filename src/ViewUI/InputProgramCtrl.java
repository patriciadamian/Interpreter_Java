package ViewUI;

import Controller.Controller;
import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.MyLibMap;
import Domain.DataStructures.Heap.MyLibHeap;
import Domain.DataStructures.Latch.MyLibLatch;
import Domain.DataStructures.List.IList;
import Domain.DataStructures.List.MyLibList;
import Domain.DataStructures.Stack.MyLibStack;
import Domain.Expressions.*;
import Domain.ProgramState.PrgState;
import Domain.Stmt.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created by Patri on 1/19/2016.
 */
public class InputProgramCtrl {
    private Controller ctrl;
    public TextArea preview;

    public void init(Controller ctrl){
        this.ctrl = ctrl;
    }

    public void actionBack(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void actionNewProgram(ActionEvent event) throws IOException {
        IStmt prgStatement = newStatement("New Program");

//        IStmt st1 = new AssignStmt("v", new ConstExpr(10));
//        IStmt st2 = new NewStmt("a", new ConstExpr(22));
//        IStmt st3 = new AssignStmt("v", new ConstExpr(32));
//        IStmt st4 = new PrintStmt(new VarExpr("v"));
//        IStmt st5 = new PrintStmt(new ReadHeapExpr("a"));
//        IStmt st8 = new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ConstExpr(30)), new CompStmt(st3, new CompStmt(st4, st5))));
//        IStmt st6 = new PrintStmt(new VarExpr("v"));
//        IStmt st7 = new PrintStmt(new ReadHeapExpr("a"));
//        IStmt prgStatement = new CompStmt(st1, new CompStmt(st2, new CompStmt(st8, new CompStmt(st6,new CompStmt (st7, new CompStmt(new SkipStmt(), new CompStmt(new SkipStmt(), new SkipStmt() )))))));


        MyLibStack stk = new MyLibStack<>();
        MyLibMap symTbl = new MyLibMap<>();
        MyLibList out = new MyLibList();
        MyLibHeap heap = new MyLibHeap();
        MyLibLatch latch = new MyLibLatch();
        MyLibMap<Expr, IStmt> tbl = new MyLibMap<>();

        MyLibMap fileT = new MyLibMap<>();
//        IStmt prgStatement =new CompStmt(new OpenFileStmt("a.txt"),
//                new CompStmt(
//                        new WriteFileStmt("a.txt", new ConstExpr(2)),
//                        new CompStmt(
//                                new WriteFileStmt("a.txt", new ConstExpr(10)),
//                                new CompStmt(
//                                        new WriteFileStmt("a.txt", new ConstExpr(5)),
//                                        new CompStmt(
//                                                new WriteFileStmt("a.txt", new ConstExpr(1)), new CloseFileStmt("a.txt"))))));
//

//        IStmt prgStatement = new CompStmt(new NewStmt("v1", new ConstExpr(2)),
//                new CompStmt(new NewStmt("v2", new ConstExpr(3)),
//                        new CompStmt(new NewStmt("v3", new ConstExpr(4)),
//                                new CompStmt(new NewLatchStmt(new ConstExpr(3), "crt"),
//                                        new CompStmt(new ForkStmt(new WriteHeapStmt("v1", new ArithExpr(new ReadHeapExpr("v1"), new ConstExpr(10), "*") )),
//                                                new CompStmt(new PrintStmt(new ReadHeapExpr("v1")),
//                                                        new CompStmt(new CountDownStmt("cnt"),
//                                                                new CompStmt(new ForkStmt(new WriteHeapStmt("v2", new ArithExpr(new ReadHeapExpr("v2"), new ConstExpr(10), "*"))),
//                                                                        new CompStmt(new PrintStmt(new ReadHeapExpr("v2")),
//                                                                                new CompStmt(new CountDownStmt("cnt"),
//                                                                                        new CompStmt(new ForkStmt(new WriteHeapStmt("v3", new ArithExpr(new ReadHeapExpr("v3"), new ConstExpr(10), "*"))),
//                                                                                                new CompStmt(new PrintStmt(new ReadHeapExpr("v3")),
//                                                                                                        new CompStmt(new CountDownStmt("cnt"),
//                                                                                                                new CompStmt(new AwaitStmt("cnt"),
//                                                                                                                        new CompStmt(new PrintStmt(new VarExpr("cnt")),
//
//
//                                                                                                               new CompStmt(new CountDownStmt("cnt"), new PrintStmt(new VarExpr("cnt"))))))))))))))))));
//        IStmt first = new CompStmt(new NewStmt("v1", new ConstExpr(2)), new CompStmt(new NewStmt("v2", new ConstExpr(3)), new CompStmt(new NewStmt("v3", new ConstExpr(4)), new NewLatchStmt("cnt", 3))));
//        ForkStmt fork1 = new ForkStmt(new CompStmt(new WriteHeapStmt("v1", new ArithExpr(new ReadHeapExpr("v1"), new ConstExpr(10), "*")), new CompStmt(new PrintStmt(new ReadHeapExpr("v1")), new CountDownStmt("cnt"))));
//        ForkStmt fork2 = new ForkStmt(new CompStmt(new WriteHeapStmt("v2", new ArithExpr(new ReadHeapExpr("v2"), new ConstExpr(10), "*")), new CompStmt(new PrintStmt(new ReadHeapExpr("v2")), new CountDownStmt("cnt"))));
//        ForkStmt fork3 = new ForkStmt(new CompStmt(new WriteHeapStmt("v3", new ArithExpr(new ReadHeapExpr("v3"), new ConstExpr(10),"*")), new CompStmt(new PrintStmt(new ReadHeapExpr("v3")), new CountDownStmt("cnt"))));
//        IStmt last = new CompStmt(new AwaitStmt("cnt"), new CompStmt(new PrintStmt(new VarExpr("cnt")), new CompStmt(new CountDownStmt("cnt"), new PrintStmt(new VarExpr("cnt")))));
//        IStmt prgStatement = new CompStmt(first, new CompStmt(fork1, new CompStmt(fork2, new CompStmt( fork3, last))));

        List<PrgState> prgStates = new ArrayList<>();

        PrgState prg = new PrgState(stk, symTbl, out, heap, fileT, latch, prgStatement, 1);
        prgStates.add(prg);

        ctrl.getRepo().setPrgList(prgStates);

        this.preview.setText(prgStatement.toString());

    }


    public String newString(String text) throws IOException {
        TextInputDialog inputDialog = new TextInputDialog("");
        inputDialog.setContentText(text);

        Optional<String> result = inputDialog.showAndWait();
        if(result.isPresent()){
            return result.get();
        }
        throw new IOException();
    }


    public IStmt newStatement(String text) throws IOException {
        String[] items = {"Compound", "Assignment", "Fork", "If", "If Then Skip", "Print", "Increment", "While", "Switch", "New", "Write Heap"};

        ChoiceDialog choiceStmt = new ChoiceDialog(items[0], items);
        choiceStmt.setTitle("New Statement");
        choiceStmt.setContentText(text);

        Optional<String> result = choiceStmt.showAndWait();
        if(result.isPresent()){
            String choice = result.get();

            if(choice.equals("Compound")){
                IStmt first = newStatement("First Statement: ");
                IStmt second = newStatement("Second Statement: ");

                return new CompStmt(first, second);
            }

            if (choice.equals("Assignment")) {
                String id = newString("Variable name");
                Expr expression = newExpression("Value");
                return new AssignStmt(id, expression);
            }
            if (choice.equals("If")) {
                Expr expression = newExpression("Expression");
                IStmt thenS = newStatement("Then statement");
                IStmt elseS = newStatement("Else statement");
                return new IfStmt(expression, thenS, elseS);
            }
            if (choice.equals("If Then Skip")) {
                Expr expression = newExpression("Expression");
                IStmt thenS = newStatement("Then statement");
                return new IfSkipStmt(expression, thenS);
            }
            if (choice.equals("Print")) {
                Expr expression = newExpression("Expression");
                return new PrintStmt(expression);
            }
            if (choice.equals("Increment")) {
                Expr expression = newExpression("Expression");
                return new IncStmt(expression);
            }
            if (choice.equals("While")) {
                Expr exp = newExpression("While Expression");
                IStmt stmt = newStatement("Statement");
                return new WhileStmt(exp, stmt);
            }
            if (choice.equals("Switch")) {
                String varname = newString("Varname");
                Expr exp;
                String opt;
                IStmt stmt;
                IDictionary<Expr, IStmt> tbl = new MyLibMap<>();
                while (true) {
                    exp = newExpression("Expression (case)");
                    stmt = newStatement("Statement");
                    try {
                        tbl.add(exp, stmt);
                    } catch (IsFullDictException e) {
                        System.out.println("Symbol Table is full!");
                    }

                    System.out.println("Another CASE? (y/n)");
                    opt = newString("Option");
                    if (opt.equals("n")) {
                        break;
                    }
                }
                stmt = newStatement("Default Statement");
                return new SwitchStmt(varname, tbl, stmt);
            }
            if (choice.equals("New")) {
                String varname = newString("Varname");
                Expr exp = newExpression("Expression");
                return new NewStmt(varname, exp);
            }
            if (choice.equals("Write Heap")) {
                String varname = newString("Varname");
                Expr exp = newExpression("Expression");
                return new WriteHeapStmt(varname, exp);
            }
            if (choice.equals("Fork")) {
                IStmt stmt = newStatement("Statement");
                return new ForkStmt(stmt);
            }

        }

        throw new IOException();
    }


    public Expr newExpression(String text) throws IOException {
        String[] items = {"Arithmetical", "Constant", "Variable", "Boolean", "Logical", "Read", "Read Heap"};

        ChoiceDialog choiceDialog = new ChoiceDialog(items[0], items);
        choiceDialog.setTitle("New Expression");
        choiceDialog.setContentText("Expression");

        Optional<String> result = choiceDialog.showAndWait();

        if(result.isPresent()){
            String choice = result.get();
            if(choice.equals("Arithmetical")){
                String operator = newString("Operator: ");
                Expr left = newExpression("Left operand: ");
                Expr right = newExpression("Right operand: ");
                return new ArithExpr(left, right, operator);
            }

            if (choice.equals("Constant")) {
                int constant = Integer.parseInt(newString("Constant value"));
                return new ConstExpr(constant);
            }
            if (choice.equals("Variable")) {
                String id = newString("Variable id");
                return new VarExpr(id);
            }
            if (choice.equals("Boolean")) {
                String operator = newString("Operator");
                Expr left = newExpression("Left operand");
                Expr right = newExpression("Right operand");
                return new BoolExpr(left, right, operator);
            }
            if (choice.equals("Logical")) {
                String operator = newString("Operator");
                Expr left = newExpression("Left operand");
                if (!operator.equals("!")) {
                    Expr right = newExpression("Right operand");
                    return new LogicalExpr(left, right, operator);
                }
                return new LogicalExpr(left, operator);
            }
            if (choice.equals("Read")) {
                int no = Integer.parseInt(newString("Constant value"));
                return new ReadExpr(no);
            }
            if (choice.equals("Read Heap")) {
                String varname = newString("Varname");
                return new ReadHeapExpr(varname);
            }
        }
        throw new IOException();

        }

}





