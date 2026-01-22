public class Deadlines extends Task{
    String by;
    public Deadlines(String name, String by) {
        super(name, 'D');
        this.by = by;
        String output = String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list", getStatus(), totalTask());
        System.out.println(output);
    }

    @Override
    public String getStatus() {
        String output = String.format("%s (by: %s)", super.getStatus(), this.by);
        return output;
    }

}
