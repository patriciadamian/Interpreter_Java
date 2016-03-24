package Ui;

import Controller.Controller;
import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.MyLibMap;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.Heap.MyLibHeap;
import Domain.DataStructures.Latch.MyLibLatch;
import Domain.DataStructures.List.IList;
import Domain.DataStructures.List.IndexOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.DataStructures.List.MyLibList;
import Domain.DataStructures.Stack.EmptyStackException;
import Domain.DataStructures.Stack.MyLibStack;
import Domain.Expressions.*;
import Domain.MyBuffer;
import Domain.ProgramState.PrgState;
import Domain.Stmt.*;
import Repository.IRepository;
import Repository.Repository;
import Controller.StmtExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Repository.RepoException;
/**
 * Created by Patri on 10/26/2015.
 */
public class Ui {
    private Controller ctrl;
    //private IRepository repo;
    private Scanner scanner;
  //  private PrgState currPrg;


    private String mainMenu =  "\nMAIN MENU\n" +
            "1. Input a program\n" +
            "2. One-step execution(only when debug is on)\n" +
            "3. Execute program \n" +
            "4. Serialize program\n" +
            "5. Deserialize program\n" +
            "6. Write to file\n" +
            "0. Exit";

    private String stmtMenu = "1. COMPOUND statement.\n" +
            "2. ASSIGN statement.\n" +
            "3. IF statement. \n" +
            "4. PRINT statement. \n" +
            "5. WHILE statement. \n" +
            "6. SWITCH statement. \n" +
            "7. SKIP statement. \n" +
            "8. NEW statement. \n" +
            "9. WRITE HEAP statement. \n" ;

    private String expMenu = "1. Arithmetical expression. \n" +
            "2. Constant expression.\n" +
            "3. Variable expression. \n" +
            "4. Boolean expression.\n" +
            "5. Logical expression. \n" +
            "6. Read expression. \n" +
            "7. Read heap expression. \n" ;



    public Ui(){
        //repo = new Repository();
        //ctrl = new Controller(repo);
        scanner = new Scanner(System.in);
    }

//    private int readInteger() throws WrongDataTypeException{
//        return scanner.nextInt();
//    }

    private void print(String message) {
        System.out.println(message);
    }

    private String readString(String message) {
        try {
            print(message);
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            return bufferRead.readLine();
        } catch (IOException e) {
            print(e.getMessage());
        }
        return "";
    }

    private Integer readInteger(String message) throws WrongDataTypeException {
        try {
            return Integer.parseInt(readString(message));
        } catch (NumberFormatException e) {
            throw new WrongDataTypeException("");
        }
    }

    private void toggleLogFlag(){
        if (ctrl.isLogFlag()){ print("Log mode: ON"); }
        else {print("Log mode OFF");}
        print("1. Change Log Flag");
        print("0. Do not change");
    }

    private void mainMenu() throws RepoException, WrongDataTypeException, IndexOutOfRangeException, NotKeyException, IsFullListException, IsFullDictException, HeapOutOfRangeException, EmptyStackException, InterruptedException {
        int option;
        System.out.println(mainMenu);

        try {
            option = readInteger("Choose an option:");
            if (option != 0 ) switch (option) {
                case 1: {
                    inputPrg();
                    //ctrl.getRepo().example2();
                    break;
                }
                case 2: {
                    this.toggleLogFlag();
                    try {
                        int op = readInteger("Choose: ");
                        if (op == 1) {
                            ctrl.changeLogFlag();
                        } else {
                            break;
                        }
                    } catch (WrongDataTypeException e) {
                        print("Input invalid");
                    } finally {
                        oneStepUi();
                        if (ctrl.isLogFlag()) {
                            ctrl.getRepo().writeToFile();
                        } else {
                            print(ctrl.getRepo().getPrgList().toString());
                        }
                        break;
                    }
                }
                case 3: {
                    this.toggleLogFlag();
                    try {
                        int op = readInteger("Choose: ");
                        if (op == 1) {
                            ctrl.changeLogFlag();
                        } else {
                            break;
                        }
                    } catch (WrongDataTypeException e) {
                        print("Input invalid");
                    } finally {
                        allStepUi();
                        if (ctrl.isLogFlag()) {
                            ctrl.getRepo().writeToFile();
                        } else {
                            print(ctrl.getRepo().getPrgList().toString());
                        }
                        break;
                    }
                }
                case 4: {
                    ctrl.getRepo().serializePrg();
                    print("Serialized!");
                    break;
                }
                case 5: {
                    //List<PrgState> desStates;
//                    ctrl.getRepo().deserializePrg();
//                    System.out.println(ctrl.getRepo().getPrgList().toString());
                    Repository repo = new Repository();
                    repo.deserializePrg();
                    ctrl = new Controller(repo);
                    System.out.println(ctrl.getRepo().getPrgList().toString());

                    break;
                }
                case 6: {
                    ctrl.getRepo().writeToFile();
                    break;
                }
                case 0: {
                    break;
                }
                default:
                    System.out.println("Invalid option, please try again!\n");
            }
            mainMenu();
        }
        catch (RepoException e){
            print("No program state added" );
        }
        catch (WrongDataTypeException e){
            print("Invalid option.");
            mainMenu();
        }
        catch (IOException e){
            print("No program");
            mainMenu();
        }
        catch (NullPointerException e){
            print("Deserializare ex");
            mainMenu();
        }


    }


    private void oneStepUi() throws RepoException, IOException, IndexOutOfRangeException, HeapOutOfRangeException, EmptyStackException, InterruptedException {
        try {
            ctrl.oneStep();
            //ctrl.getRepo().serializePrg();
        } catch (NotKeyException e){
            System.out.println("Key not found!");
        }
    }


    private void allStepUi() throws HeapOutOfRangeException, EmptyStackException, InterruptedException {
        try {
            ctrl.allStep();
            //ctrl.getRepo().serializePrg();
        //}catch (StmtExecutionException e){
            System.out.println("\nFINISHED\n");
            //ctrl.getRepo().serializePrg();
            //currPrg = null;
        }catch (VarNotDefinedException e){
            System.out.println("Variable not defined!");
        }catch (DivisionByZeroException e){
            System.out.println("Division by zero!");
        }catch (IsFullDictException e){
            System.out.println("Full symbol table!");
        }catch (IsFullListException e){
            System.out.println("Full output list!");
        }catch (NotKeyException e){
            System.out.println("Key not found!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RepoException e) {
            e.printStackTrace();
        } catch (IndexOutOfRangeException e) {
            e.printStackTrace();
        }
    }

    private IStmt inputStmt() {

        int option;
        System.out.println(stmtMenu);

        try {

            option = readInteger("Choose an option:");
            IStmt crnt;

            switch (option) {
                case 1: {
                    crnt = cmpStmt();
                    break;
                }

                case 2: {
                    crnt = assignStmt();
                    break;
                }

                case 3: {
                    crnt = ifStmt();
                    break;
                }

                case 4: {
                    crnt = printStmt();
                    break;
                }

                case 5: {
                    crnt = whileStmt();
                    break;
                }

                case 6: {
                    crnt = switchStmt();
                    break;
                }

                case 7: {
                    crnt = skipStmt();
                    break;
                }

                case 8: {
                    crnt = newStmt();
                    break;
                }

                case 9: {
                    crnt = writeHeapStmt();
                    break;
                }
                default:
                    System.out.println("Invalid option! Please try again.\n");
                    crnt = inputStmt();


            }

            return crnt;

        } catch (WrongDataTypeException e) {
            System.out.println("Invalid input!");
        }
        return inputStmt();
    }


    private ArithExpr arExp() throws WrongDataTypeException{
        System.out.println("Operator: 1 for + , 2 or - , 3 for * , 4 for / \n");
        //System.out.println("Type operator: ");
        String operator = readString("Type operator: ");
        System.out.println("Left operand: ");
        Expr left = inputExpr();
        System.out.println("Right operand: ");
        Expr right = inputExpr();
        return new ArithExpr(left, right, operator);

    }

    private ConstExpr cstExp() throws WrongDataTypeException{
      //  System.out.println("Constant: ");
        int cst = readInteger("Constant: ");
        return new ConstExpr(cst);
    }

    private VarExpr varExp() throws WrongDataTypeException{
        System.out.println("Variable id: ");
        String id = scanner.next();
        return new VarExpr(id);
    }

    private BoolExpr boolExp()throws WrongDataTypeException{
        System.out.println("Options: <, >, <=, >=, ==, != \n");
        System.out.println("Type operator: ");
        String operator = scanner.next();
        System.out.println("Left operand: ");
        Expr left = inputExpr();
        System.out.println("Right operand: ");
        Expr right = inputExpr();
        return new BoolExpr(left, right, operator);

    }

    private LogicalExpr logicalExp()throws WrongDataTypeException{
        System.out.println("Options: &&, ||, !\n");
        System.out.println("Operator:");
        String operator = scanner.next();
        System.out.println("Left operand: ");
        Expr left = inputExpr();
        if(!operator.equals("!")){
            System.out.println("Right operand: ");
            Expr right = inputExpr();
            return new LogicalExpr(left, right, operator);
        }
        return new LogicalExpr(left, operator);
    }


    private ReadExpr readExpr() throws WrongDataTypeException{
      //  System.out.println("Input an integer: ");
        Integer nr = readInteger("Input an integer: ");
        return  new ReadExpr(nr);
    }

    private ReadHeapExpr readHeap(){

        String varname = readString("Var name: ");
        return new ReadHeapExpr(varname);
    }



    private Expr inputExpr() {

        int option;
        System.out.println(expMenu);
        //System.out.println("Choose an option: ");
        try {
            option = readInteger("Choose an option: ");
            Expr exp;


            switch (option) {
                case 1: {
                    exp = arExp();
                    break;
                }

                case 2: {
                    exp = cstExp();
                    break;
                }

                case 3: {
                    exp = varExp();
                    break;
                }

                case 4: {
                    exp = boolExp();
                    break;
                }

                case 5: {
                    exp = logicalExp();
                    break;
                }

                case 6: {
                    exp = readExpr();
                    break;
                }

                case 7: {
                    exp = readHeap();
                    break;
                }


                default: {
                    System.out.println("Invalid option! Please try again.\n");
                    exp = inputExpr();
                }
            }

            return exp;

        } catch (WrongDataTypeException e) {
            System.out.println("Invalid option! Please try again.\n");
        }
        return inputExpr();
    }


    private CompStmt cmpStmt(){
        System.out.println("First statement:");
        IStmt first = inputStmt();
        System.out.println("Second statement:");
        IStmt snd = inputStmt();
        return new CompStmt(first, snd);

    }

    private AssignStmt assignStmt(){
        System.out.println("Variable name:");
        String id = scanner.next();
        System.out.println("Variable value:");
        Expr exp = inputExpr();
        return new AssignStmt(id, exp);
    }

    private IfStmt ifStmt(){
        System.out.println("IF Expression:");
        Expr exp = inputExpr();
        System.out.println("THEN statement:");
        IStmt thenS = inputStmt();
        System.out.println("ELASE statement:");
        IStmt elseS = inputStmt();
        return new IfStmt(exp, thenS, elseS);
    }

    private PrintStmt printStmt(){
        System.out.println("Expression:");
        Expr exp = inputExpr();
        return new PrintStmt(exp);
    }

//    private IncStmt incStmt(){
//        System.out.println("Expression:");
//        Expr exp = inputExpr();
//        return new IncStmt(exp);
//    }


    private WhileStmt whileStmt(){
        System.out.println("Expression:");
        Expr e = inputExpr();
        System.out.println("Statement:");
        IStmt stmt = inputStmt();
        return new WhileStmt(e,stmt);
    }

    private SwitchStmt switchStmt() throws WrongDataTypeException{
        System.out.println("Variable name: ");
        String varname = scanner.next();
        Expr exp;
        String opt;
        IStmt stmt;
        IDictionary<Expr, IStmt> tbl = new MyLibMap<>();
        while(true){
            System.out.println("CASE expression: ");
            exp = inputExpr();
            System.out.println("Statement: ");
            stmt = inputStmt();
            try {
                tbl.add(exp, stmt);
            }catch (IsFullDictException e){
                System.out.println("Full symbol table!");
            }
            System.out.println("Another CASE? (y/n)");
            opt = scanner.next();
            if(opt.equals("n")){break;}
        }
        System.out.println("DEFAULT statement: ");
        stmt = inputStmt();
        return  new SwitchStmt(varname, tbl, stmt);
    }

    private SkipStmt skipStmt(){
        return new SkipStmt();
    }

    private NewStmt newStmt() throws WrongDataTypeException {
        String name = readString("Var name:");
        print("Right side:");
        Expr exp = inputExpr();
        return new NewStmt(name, exp);
    }

    private WriteHeapStmt writeHeapStmt() throws WrongDataTypeException {
        String name = readString("Var name:");
        print("Right side:");
        Expr exp = inputExpr();
        return new WriteHeapStmt(name, exp);
    }



    private void inputPrg() throws RepoException, IOException, IsFullListException, IndexOutOfRangeException, IsFullDictException {


        IStmt st1 = new AssignStmt("v", new ConstExpr(10));
        IStmt st2 = new NewStmt("a", new ConstExpr(22));
        IStmt st3 = new AssignStmt("v", new ConstExpr(32));
        IStmt st4 = new PrintStmt(new VarExpr("v"));
        IStmt st5 = new PrintStmt(new ReadHeapExpr("a"));
        IStmt st8 = new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ConstExpr(30)), new CompStmt(st3, new CompStmt(st4, st5))));
        IStmt st6 = new PrintStmt(new VarExpr("v"));
        IStmt st7 = new PrintStmt(new ReadHeapExpr("a"));
        IStmt prgStatement = new CompStmt(st1, new CompStmt(st2, new CompStmt(st8, new CompStmt(st6,new CompStmt (st7, new CompStmt(new SkipStmt(), new CompStmt(new SkipStmt(), new SkipStmt() )))))));

        List<PrgState> programs = new ArrayList<>();

        programs.add(new PrgState(new MyLibStack<>(), new MyLibMap<>(),new MyLibList<>(),  new MyLibHeap<>(), new MyLibMap<>(), new MyLibLatch<>(), prgStatement));
        print(programs.toString());
        Repository repo = new Repository(programs);

        ctrl = new Controller(repo);
        ctrl.serializeProgram();

    }

    public void run() throws IndexOutOfRangeException, NotKeyException, RepoException, WrongDataTypeException, IsFullListException, IsFullDictException, HeapOutOfRangeException, EmptyStackException, InterruptedException {
        mainMenu();
    }






}
