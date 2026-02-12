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
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        response = ui.printTasks(taskList.get());
        assert response.startsWith("Here are the tasks in your list:") : "Invalid response format for List command";
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
