import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Handles saving and loading tasks to and from a text file
 */
public class Storage {
    private final Scanner fileIn;
    private final UI ui;

    /** Default file path */
    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";

    /**
     * Initialises Storage
     * @param ui                        UI declared in main
     * @throws FileNotFoundException    Thrown if storage text file does not exist
     */
    public Storage(UI ui) throws FileNotFoundException{
        this.ui = ui;
        fileIn = new Scanner(new InputStreamReader(new FileInputStream(DEFAULT_STORAGE_FILEPATH), StandardCharsets.UTF_8));
    }

    /**
     * Reads tasks from text file and saves them in TaskList
     * @param taskList  List of tasks
     */
    public void load(TaskList taskList){
        String info, curLine;
        try{
            curLine = fileIn.nextLine();
        }catch(NoSuchElementException e){
            return;
        }
        while(curLine != null){
            try{
                info = curLine.substring(7);
            }catch(IndexOutOfBoundsException e){
                curLine = fileIn.nextLine();
                continue;
            }
            if(info.equals("")){
                curLine = fileIn.nextLine();
                continue;
            }
            if(info.contains("(at:")){
                taskList.addEventFromText(info);
            }else if(info.contains("(by:")){
                taskList.addDeadlineFromText(info);
            }else{
                taskList.addTodofromText(info);
            }
            try{
                curLine = fileIn.nextLine();
            }catch(NoSuchElementException e){
                return;
            }
        }
    }

    /**
     * Saves tasks in TaskList to a text file
     * @param taskList  List of tasks
     */
    public void save(TaskList taskList){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(DEFAULT_STORAGE_FILEPATH), StandardCharsets.UTF_8))) {
            for(Task t: taskList.getTasks()){
                writer.write(t.toString());
            }
        }catch(FileNotFoundException e){
            ui.showFileFailedToOpenMsg();
            return;
        }catch(IOException f){
            ui.showFileFailedToWriteMsg();
            return;
        }
        ui.showFileSaveSuccessfulMsg();
    }
}
