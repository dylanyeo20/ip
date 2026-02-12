package command;

import duke.Storage;
import duke.UI;
import task.TaskList;


/**
 * Class for List commands
 */
public class ListCommand extends Command {
    private String response;

    /**
     * Executes list tasks
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    public void execute(TaskList taskList, UI ui, Storage storage) {
        response = ui.printTasks(taskList.get());
    }

    /**
     * Returns string of Command
     *
     * @return
     */
    public String getString() {
        return response;
    }
}
