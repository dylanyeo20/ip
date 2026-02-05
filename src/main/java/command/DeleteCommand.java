package command;

import duke.Storage;
import task.Task;
import task.TaskList;
import duke.UI;

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
        Task deletedTask = tasks.deleteTask(storage, index);
        response = ui.printDeleteTask(deletedTask);
    }

    @Override
    public String getString() {
        return response;
    }
}
