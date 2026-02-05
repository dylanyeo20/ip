package task;

/**
 * Represents a task to be completed.
 */
public class ToDos extends Task {

    /**
     * Creates a new ToDos task from user input.
     *
     * @param name Description of task
     */
    public ToDos(String name) {
        super(name, 'T');
    }

    /**
     * Creates a ToDos task loaded from data file.
     *
     * @param name       Description of task
     * @param isTaskDone Task completion status
     */
    public ToDos(String name, boolean isTaskDone) {
        super(name, 'T', isTaskDone);
    }

    @Override
    public String dataInputString() {
        int isDone = (super.isDone ? 1 : 0);
        return type + " | " + isDone + " | " + name;
    }
}
