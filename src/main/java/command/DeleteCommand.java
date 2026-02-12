package command;

import duke.Storage;
import duke.UI;
import task.Task;
import task.TaskList;


/**
 * Executes the delete command
 */
public class DeleteCommand extends Command {
    private int index;
    private String response;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        Task deletedTask = tasks.deleteTask(storage, index);
        response = ui.printDeleteTask(deletedTask);
        assert response.startsWith("Got it. I've added this task:") : "Invalid response format for Add Command";
    }

    @Override
    public String getString() {
        return response;
    }
}
