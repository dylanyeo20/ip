package Duke;

import java.time.LocalDateTime;

/**
 * Represents an Event task that has a start and end date/time
 */
public class Event extends Task {
    private final LocalDateTime from, to;

    /**
     * Creates a new Event task from user input.
     *
     * @param name Description of task
     * @param from Start date/time
     * @param to   End date/time
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name, 'E');
        this.from = from;
        this.to = to;
        String output =
                String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list", getStatus(),
                        totalTask());
        System.out.println(output);

    }

    /**
     * Creates an Event task loaded from data file.
     *
     * @param name       Description of task
     * @param from       Start date/time
     * @param to         End date/time
     * @param isTaskDone Task completion status
     */
    public Event(String name, LocalDateTime from, LocalDateTime to, boolean isTaskDone) {
        super(name, 'E', isTaskDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatus() {
        String output =
                String.format("%s (from: %s to: %s)", super.getStatus(), from.format(DATE_DISPLAY_FORMAT),
                        to.format(DATE_DISPLAY_FORMAT));
        return output;
    }

    @Override
    public String dataInputString() {
        int isDone = (super.isDone ? 1 : 0);
        return type + " | " + isDone + " | " + name + " | " + from.format(DATE_DATA_FORMAT) + " | " + to.format(DATE_DATA_FORMAT);
    }


}
