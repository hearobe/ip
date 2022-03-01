import java.io.FileNotFoundException;

/**
 * The Duke program acts as a planner to help users
 * keep track of the tasks they need to do
 *
 * @author  Tan Yu Ling
 */
public class Main {
    private UI ui;
    private Parser parser;

    /**
     * Main function of Duke
     *
     * @param args  input arguments
     */
    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * handles the full workflow of Duke
     */
    public void run(){
        start();
        runUntilExit();
        exit();
    }

    /**
     * Loop to get user commands until exit command is called
     */
    public void runUntilExit(){
        String rawInput;
        boolean isToExit = false;
        while(!isToExit){
            rawInput = ui.getUserCommand();
            isToExit = parser.executeCommand(rawInput);
        }
    }

    /**
     * Initialises UI, Storage, TaskList and Parser classes
     * Initialises existing saved tasks from text file
     */
    public void start(){
        try{
            ui = new UI();
            Storage storage = new Storage(ui);
            TaskList taskList = new TaskList(ui);
            storage.load(taskList);
            parser = new Parser(ui, taskList, storage);
            ui.showWelcomeMsg();
        } catch (FileNotFoundException e){
            ui.showFileFailedToOpenMsg();
        }
    }

    /**
     * Handles exit of Duke
     */
    public void exit(){
        ui.showGoodbyeMsg();
    }
}
