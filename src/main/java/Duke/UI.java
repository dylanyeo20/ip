package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    Scanner sc;
    public static final DateTimeFormatter DATE_DATA_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private static final String line = "____________________________________________________________";

    public UI() {
        sc = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        String chatbot = "Dylan";
        System.out.println(String.format("____________________________________________________________\n" + " Hello! " +
                "I'm %s\n" + " What can I do for you?\n" +
                "____________________________________________________________\n", chatbot));
    }

    public String get() {
        String instr = sc.nextLine();
        return instr;
    }

    public void print(ArrayList<Task> listOfThingsToDo) {
        System.out.println("Here are the tasks in your list:");
        for (Task t : listOfThingsToDo) {
            t.printTask();
        }
    }


    public void run(Storage storage, TaskList taskList) {
        String input;

        while (sc.hasNext()) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            }

            System.out.println(line);

            if (input.equals("list")) {
                print(taskList.get());
                System.out.println(line);
                continue;
            }

            try {
                Parser.doCommand(input, storage, taskList, this);
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            } catch (DateTimeParseException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please input Date & Time in: dd/mm/yyyy HHMM");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            } finally {
                System.out.println(line);
            }
        }

        System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);
    }

}
