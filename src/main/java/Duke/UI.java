package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void printWelcomeMessage() {
        String chatbot = "Dylan";
        System.out.println(String.format("____________________________________________________________\n" +
                " Hello! I'm %s\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n", chatbot));
    }

    public String get() {
        String instr = sc.nextLine();
        return instr;
    }

    /**
     * Print list of things to do (list command)
     *
     * @param listOfThingsToDo List of tasks to do.
     */
    public void print(ArrayList<Task> listOfThingsToDo) {
        System.out.println("Here are the tasks in your list:");
        for(Task t : listOfThingsToDo) {
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

        while(sc.hasNext()) {
            input = sc.nextLine();

            if (input.equals("bye")) break;

            System.out.println(LINE);

            if (input.equals("list")) {
                print(taskList.get());
                System.out.println(LINE);
                continue;
            }

            try {
                Parser.doCommand(input, storage, taskList);
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

        System.out.println(LINE +
                "\n Bye. Hope to see you again soon!\n" + LINE);
    }

}
