package duke;

import exception.DukeException;
import task.Task;
import task.TaskList;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
    public String printWelcomeMessage() {
        String chatbot = "Dylan";
        return String.format(" Hello! " + "I'm %s\n" + " What can I do for you?\n", chatbot);
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

    /**
     * Print list of things to do (list command)
     *
     * @param listOfThingsToDo List of tasks to do.
     */
    public void print(ArrayList<Task> listOfThingsToDo) {
        System.out.println("Here are the tasks in your list:");
        for (Task t : listOfThingsToDo) {
            t.printTask();
        }
    }


    /**
     * Runs the main loop of the chatbot.
     * <p>
     * Reads the input, and calls execute command in Parser
     *
     * @param storage
     * @param taskList
     */
    public void run(Storage storage, TaskList taskList) {
        String input;

        while (sc.hasNext()) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            }

            System.out.println(LINE);

            if (input.equals("list")) {
                print(taskList.get());
                System.out.println(LINE);
                continue;
            }

            try {
                Parser.parse(input);
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            } catch (DateTimeParseException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please input Date & Time in: dd/mm/yyyy HHMM");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            } finally {
                System.out.println(LINE);
            }
        }

        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }

}
