package command;

import duke.Storage;
import duke.UI;
import task.TaskList;


/**
 * Abstract class for commands
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws Exception;

    public abstract String getString();
}

