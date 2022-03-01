import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles receiving and sending of messages to and from the user
 * through command line
 */
public class UI {
    private final Scanner in;
    private final PrintStream out;

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    /**
     * Initialises UI
     */
    public UI(){
        this.in = new Scanner(System.in);
        this.out = new PrintStream(System.out);
    }

    /**
     * Display welcome message
     */
    public void showWelcomeMsg(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.print("Hello from" + LS + logo + LS +
                "____________________" + LS +
                "Hello! I'm Duke" + LS +
                "What can I do for you?" + LS);
    }

    /**
     * Display exit message
     */
    public void showGoodbyeMsg(){
        out.print("____________________" + LS +
                "Bye. Hope to see you again soon!" + LS +
                "____________________");
    }

    /**
     * Retrieve user input
     */
    public String getUserCommand(){
        return in.nextLine().trim();
    }

    /**
     * Display error when file cannot be opened
     */
    public void showFileFailedToOpenMsg(){
        out.print("// Warning: File could not be opened" + LS);
    }

    /**
     * Display error when file cannot be written to
     */
    public void showFileFailedToWriteMsg(){
        out.print("// Warning: Could not write to file" + LS);
    }

    /**
     * Display success when tasks have been saved
     */
    public void showFileSaveSuccessfulMsg(){
        out.print("Saved successfully!" + LS);
    }

    /**
     * Display error when file cannot be decoded
     */
    public void showDecodeErrorMsg(){
        out.print("// Warning: Line in text file cannot be decoded and was skipped" + LS);
    }

    /**
     * Display error when user command is formatted incorrectly
     */
    public void showIncorrectFormatMsg(){
        out.print("Incorrect formatting. Please try again" + LS);
    }

    /**
     * Display error when task does not exist
     */
    public void showNonexistentTaskMsg(){
        out.print("This task does not exist. Please try again" + LS);
    }

    /**
     * Display success when task has been deleted
     */
    public void showSuccessfulDelete(Task removedTask, int size){
        out.printf("I've deleted this task:" + LS +
                "\t%s" + LS +
                "Now you have %d in the list." + LS, removedTask, size);
    }

    /**
     * Display error when user command does not exist
     */
    public void showInvalidCommandMsg(){
        out.print("Not a valid command" + LS);
    }

    /**
     * Display success when task has been added
     */
    public void showSuccessfulTaskAdded(Task newTask){
        out.printf("Got it. I've added this task:" + LS +
                "\t%s" + LS, newTask);
    }

    /**
     * Display success when task has been marked as completed
     */
    public void showSuccessfulMark(Task task){
        out.printf("Nice! I've marked this task as done: %s" + LS, task);
    }

    /**
     * Display success when task has been marked as incomplete
     */
    public void showSuccessfulUnmark(Task task){
        out.printf("OK, I've marked this task as not done yet: %s" + LS, task);
    }

    /**
     * Display all tasks
     */
    public void showAllTasks(TaskList taskList){
        out.print("Here are the tasks in your list:" + LS);
        ArrayList<Task> list = taskList.getTasks();
        for(int i = 0; i< list.size(); i++){
            out.printf("%d. %s" + LS, i + DISPLAYED_INDEX_OFFSET, list.get(i));
        }
    }
}
