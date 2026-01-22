import java.util.*;

public class Task {
    protected String name;
    private boolean isDone;
    protected int idx;
    char type;
    private static int count = 0;

    public Task(String name, char type) {
        this.name = name;
        isDone = false;
        idx = ++count;
        this.type = type;

    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this.getStatus());
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(getStatus());

    }
    public void printTask() {
        String output = String.format("%d. %s",this.idx, getStatus());
        System.out.println(output);
    }

    protected String isDone() {
        return this.isDone ? "x" : " ";
    }

    public String getStatus() {
        String output = String.format("[%c][%s] %s",this.type, isDone(), this.name);
        return output;
    }

    public static int totalTask() {
        return count;
    }


}
