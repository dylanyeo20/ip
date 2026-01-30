package Duke;

public class ToDos extends Task{
    public ToDos(String name) {
        super (name, 'T');
        String output = String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list", getStatus(), totalTask());
        System.out.println(output);
    }

    public ToDos(String name, boolean isTaskDone) {
        super(name, 'T', isTaskDone);
    }

    @Override
    public String dataInputString() {
        int isDone = (super.isDone ? 1 : 0);
        return type + " | " + isDone + " | " + name;
    }
}
