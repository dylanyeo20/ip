import java.util.*;

public class Task {
    private String name;
    private boolean isDone;
    private int idx;
    private static int count = 0;

    public Task(String name) {
        this.name = name;
        isDone = false;
        idx = ++count;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        printStatus();
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        printStatus();

    }
    public void printTask() {
        String output = String.format("%d. [%s] %s",this.idx, getStatus(), this.name);
        System.out.println(output);
    }

    private String getStatus() {
        return this.isDone ? "x" : " ";
    }

    public void printStatus() {
        String output = String.format("[%s] %s", getStatus(), this.name);
        System.out.println(output);
    }

    public static int totalTask() {
        return count;
    }


}
