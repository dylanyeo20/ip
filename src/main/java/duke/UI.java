package duke;

import java.util.ArrayList;
import java.util.Scanner;

import task.Task;

/**
 * Represent user interface of chatbot.
 * Handles user inputs and outputs.
 */
public class UI {
    private static final String LINE = "____________________________________________________________";


    private final Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message
     */
    public String printWelcomeMessage(ArrayList<Task> upcoming) {
        String chatbot = "Dylan";
        String welcomeMessage = String.format(" Hello! " + "I'm %s\n" + " What can I do for you?\n", chatbot);
        if (upcoming.isEmpty()) {
            return welcomeMessage;
        }

        return welcomeMessage + printUpcomingTasks(upcoming);
    }

    public String get() {
        String instr = sc.nextLine();
        return instr;
    }

    /**
     * Returns string of list of things to do
     *
     * @param tasks
     * @return
     */
    public String printTasks(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list: \n");
        for (Task task : tasks) {
            response.append(task.printTask()).append("\n");
        }
        return response.toString();
    }

    /**
     * Returns string of upcoming tasks within 7 days.
     *
     * @param upcoming
     * @return
     */
    public String printUpcomingTasks(ArrayList<Task> upcoming) {
        StringBuilder results = new StringBuilder("Here is the list of tasks due in 7 days!\n");
        for (Task t : upcoming) {
            results.append(t.printTask());
        }
        return results.toString();
    }

    /**
     * Return response of marking task as done
     *
     * @param task Task that has been mark as done
     * @return Response
     */
    public String printMarkAsDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task.getStatus();
    }

    /**
     * Return response of marking task as undone
     *
     * @param task Task that has been mark as done
     * @return Response
     */
    public String printUnmarkAsDone(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.getStatus();
    }

    /**
     * Returns response of adding task
     *
     * @param task Task that was added
     * @return Response
     */
    public String printAddTask(Task task) {
        return String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list", task.getStatus(),
                Task.totalTask());
    }

    /**
     * Returns response of deleting task
     *
     * @param task Task that was deleted
     * @return Response
     */
    public String printDeleteTask(Task task) {
        return "Noted. I've removed this task: \n" + task.getStatus() + "\nNow you have " + Task.totalTask()
                + " tasks in the list.";
    }

    /**
     * Returns bye message
     */
    public String printByeMessage() {
        return "Bye. Hope to see you again soon!";
    }
}
