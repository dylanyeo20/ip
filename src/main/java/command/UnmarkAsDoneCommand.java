package command;

import duke.Storage;
import task.Task;
import task.TaskList;
import duke.UI;

/**
 * Executes 'unmark' command
 */
public class UnmarkAsDoneCommand extends Command {
    private int index;
    private String response;

    public UnmarkAsDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        Task task = tasks.unmarkAsDone(storage, index);
        response = ui.printUnmarkAsDone(task);
    }

    @Override
    public String getString() {
        return response;
    }
}
