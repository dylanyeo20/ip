package command;

import java.util.ArrayList;

import duke.Storage;
import duke.UI;
import task.Task;
import task.TaskList;


/**
 * Executes reminder command
 */
public class ReminderCommand extends Command {
    private String response;

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        ArrayList<Task> upcomings = tasks.getUpcomingTasks();
        response = ui.printUpcomingTasks(upcomings);
    }

    @Override
    public String getString() {
        return response;
    }
}
