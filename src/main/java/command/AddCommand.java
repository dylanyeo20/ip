package command;

import java.time.LocalDateTime;

import task.Deadlines;
import task.Event;
import duke.Storage;
import task.Task;
import task.TaskList;
import task.ToDos;
import duke.UI;


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
        tasks.addTask(storage, task);
        response = ui.printAddTask(task);
    }

    @Override
    public String getString() {
        return response;
    }
}
