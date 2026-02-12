package task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with Name, Completion Status, and Index of task in TaskList.
 */
public class Task {
    public static final DateTimeFormatter DATE_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm");
    public static final DateTimeFormatter DATE_DATA_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    //Global counter for total number of Tasks
    private static int count = 0;

    protected String name;
    protected boolean isDone;
    protected int idx;
    protected char type;

    /**
     * Creates a new Task from user input.
     *
     * @param name Description of task
     * @param type Type of task (eg. Deadline, ToDos, Event)
     */
    public Task(String name, char type) {
        this.name = name;
        isDone = false;
        idx = ++count;
        this.type = type;
    }

    /**
     * Creates a new task loaded from data file.
     *
     * @param name   Description of task
     * @param type   Type of task (eg. Deadline, ToDos, Event)
     * @param isDone Task completion status
     */
    public Task(String name, char type, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        idx = ++count;
        this.type = type;
    }

    /**
     * Mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark task as undone
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Prints tasks
     */
    public String printTask() {
        String output = String.format("%d. %s\n", this.idx, getStatus());
        return output;
        //System.out.println(output);
    }

    public static int totalTask() {
        return count;
    }

    public static void reduceTask() {
        count--;
    }

    public void reduceIndex() {
        this.idx--;
    }

    protected String isDone() {
        return this.isDone ? "x" : " ";
    }

    public boolean isUpcoming() {
        return false;
    }

    /**
     * Returns the status of the task.
     * <p>
     * Format: "[Type of task] [Completion Status] Description of task"
     *
     * @return Formatted Status of task
     */
    public String getStatus() {
        String output = String.format("[%c][%s] %s", this.type, isDone(), this.name);
        return output;
    }

    /**
     * Returns a String containing information about the task for storing into data file.
     *
     * @return Formatted task information suitable for storage.
     */
    public String dataInputString() {
        int isDone = (this.isDone ? 1 : 0);
        return "Task" + " | " + isDone + " | " + name + "\n";
    }


}
