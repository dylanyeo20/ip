package command;

import java.util.ArrayList;

import duke.Storage;
import duke.UI;
import task.Task;
import task.TaskList;


/**
 * Executes find command
 */
public class FindCommand extends Command {
    private String name;
    private String response;

    public FindCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        ArrayList<Task> foundTasks = tasks.findTask(this.name);
        response = ui.printTasks(foundTasks);
        assert response.startsWith("Here are the tasks in your list:") : "Invalid response format for Find command";
    }

    @Override
    public String getString() {
        return response;
    }
}
