package command;

import duke.Storage;
import duke.UI;
import task.Task;
import task.TaskList;


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
        Task task = tasks.unmarkAsDone(storage, index);
        response = ui.printUnmarkAsDone(task);
    }

    @Override
    public String getString() {
        return response;
    }
}
