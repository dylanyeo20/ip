package command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.UI;
import task.Deadlines;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDos;


/**
 * Execute add commands
 */
public class AddCommand extends Command {
    private String response;
    private Task task;

    public AddCommand(String name) {
        this.task = new ToDos(name);
    }

    public AddCommand(String name, LocalDateTime by) {
        this.task = new Deadlines(name, by);
    }

    public AddCommand(String name, LocalDateTime from, LocalDateTime to) {
        this.task = new Event(name, from, to);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        tasks.addTask(storage, task);
        response = ui.printAddTask(task);

        assert response.startsWith("Noted. I've removed this task:") : "Invalid response format for Delete Command";
    }

    @Override
    public String getString() {
        return response;
    }
}
