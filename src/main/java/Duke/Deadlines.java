package Duke;

import java.time.LocalDateTime;

public class Deadlines extends Task {
    LocalDateTime by;

    public Deadlines(String name, LocalDateTime by) {
        super(name, 'D');
        this.by = by;
        String output = String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list",
                getStatus(), totalTask());
        System.out.println(output);
    }

    public Deadlines(String name, LocalDateTime by, boolean isTaskDone) {
        super(name, 'D', isTaskDone);
        this.by = by;
    }

    @Override
    public String getStatus() {
        String output = String.format("%s (by: %s)", super.getStatus(), by.format(DATE_DISPLAY_FORMAT));
        return output;
    }

    @Override
    public String dataInputString() {
        int isDone = (super.isDone ? 1 : 0);
        return type + " | " + isDone + " | " + name + " | " + by.format(DATE_DATA_FORMAT);
    }

}
