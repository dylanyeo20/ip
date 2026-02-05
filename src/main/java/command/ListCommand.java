package command;

import java.util.ArrayList;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.UI;


/**
 * Class for List commands
 */
public class ListCommand extends Command {
    /**
     * Executes list tasks
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ArrayList<Task> tasks = taskList.get();

    }

    /**
     * Returns string of Command
     *
     * @return
     */
    public String getString() {
        return "";
    }
}
