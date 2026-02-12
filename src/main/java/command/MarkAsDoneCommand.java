package command;

import duke.Storage;
import duke.UI;
import task.Task;
import task.TaskList;


/**
 * Executes 'mark' commands
 */
public class MarkAsDoneCommand extends Command {
    private int index;
    private String response;

    public MarkAsDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        Task task = tasks.markAsDone(storage, index);
        response = ui.printMarkAsDone(task);
    }

    @Override
    public String getString() {
        return response;
    }
}
