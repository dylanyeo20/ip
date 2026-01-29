public class Event extends Task{

    String from, to;
    public Event(String name, String from, String to) {
        super(name, 'E');
        this.from = from;
        this.to = to;
        String output = String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list", getStatus(), totalTask());
        System.out.println(output);

    }

    public Event(String name, String from, String to, boolean isTaskDone) {
        super(name, 'E', isTaskDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatus() {
        String output = String.format("%s (from: %s to: %s)", super.getStatus(), from, to);
        return output;
    }

    @Override
    public String dataInputString() {
        int isDone = (super.isDone ? 1 : 0);
        return type + " | " + isDone + " | " + name + " | " + from + " | " + to;
    }


}
