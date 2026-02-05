package command;

import duke.Storage;
import task.Task;
import task.TaskList;
import duke.UI;


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
        Task task = tasks.markAsDone(storage, index);
        response = ui.printMarkAsDone(task);
    }

    @Override
    public String getString() {
        return response;
    }
}
