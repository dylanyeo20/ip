package command;

import java.util.ArrayList;

import duke.Storage;
import task.Task;
import task.TaskList;
import duke.UI;


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
        ArrayList<Task> foundTasks = tasks.findTask(this.name);
        response = ui.printTasks(foundTasks);
    }

    @Override
    public String getString() {
        return response;
    }
}
