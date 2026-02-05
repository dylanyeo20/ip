package command;

import duke.Storage;
import task.TaskList;
import duke.UI;

/**
 * Abstract class for commands
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws Exception;

    public abstract String getString();
}

