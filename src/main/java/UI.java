import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    Scanner sc;
    public static final DateTimeFormatter DATE_DATA_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private static final String line = "____________________________________________________________";

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

    public void print(ArrayList<Task> listOfThingsToDo) {
        System.out.println("Here are the tasks in your list:");
        for(Task t : listOfThingsToDo) {
            t.printTask();
        }
    }

    public void doCommand(String command, Storage storage, TaskList taskList) throws Exception {
        Scanner sc = new Scanner(command);
        String input = sc.next();

        if (input.isBlank()) {
            throw new DukeException("Invalid Command: null!");
        }
        if (input.equals("list")) {
            print(taskList.get());
            return;
        }

        else if (input.equals("mark")){
            if (!sc.hasNextInt()) throw new DukeException(" Please give an index of task to Mark!");
            int index  = sc.nextInt() - 1;
            if (index < 0 || index >= Task.totalTask()) throw new DukeException((index + 1) + " is a invalid index!");
            taskList.markAsDone(storage, index);

        }  else if (input.equals("unmark")) {
            if (!sc.hasNextInt()) throw new DukeException(" Please give an index of task to unMark!");
            int index = sc.nextInt() - 1;
            if (index < 0 || index >= Task.totalTask()) throw new DukeException((index + 1) + " is a invalid index!");
            taskList.unmarkAsDone(storage, index);

        } else if (input.equals("delete")) {
            if (!sc.hasNextInt()) throw new DukeException(" Please give an index of task to delete!");
            int index = sc.nextInt() - 1;
            if (index < 0 || index >= Task.totalTask()) throw new DukeException((index + 1) + " is a invalid index!");
            taskList.deleteTask(storage, index);

        } else if (input.equals("todo")){//else we just add new task
            if (!sc.hasNext()) throw new DukeException(" Please give description of task");
            String name = sc.nextLine();
            if (name.isBlank()) {
                throw new DukeException("Invalid command! Missing Task name");
            }
            taskList.addTask(storage, new ToDos(name));

        } else if (input.equals("deadline")) {
            if (!sc.hasNext()) throw new DukeException(" Please give description and deadline of task");
            String[] nameAndBy = sc.nextLine().split(" /by ");
            if (nameAndBy.length != 2) throw new DukeException("Invalid command! <Description> /by <Deadline>");
            String name = nameAndBy[0].trim(), by = nameAndBy[1].trim();

            if (name.isBlank() || by.isBlank()) throw new DukeException("Name and By cannot be empty!");

            LocalDateTime dateTime = LocalDateTime.parse(by, DATE_DATA_FORMATTER);




            taskList.addTask(storage, new Deadlines(name, dateTime));

        } else if (input.equals("event")) {
            if (!sc.hasNext()) throw new DukeException(" Please give description, from, to of task");
            String[] nameAndFromAndTo = sc.nextLine().split(" /from ");
            if (nameAndFromAndTo.length != 2) throw new DukeException("Invalid syntax! <Description> /from <from> /to <to>");
            String[] FromAndTo = nameAndFromAndTo[1].split(" /to ");
            if (FromAndTo.length != 2) throw new DukeException("Invalid syntax! <Description> /from <from> /to <to>");
            String name = nameAndFromAndTo[0], from = FromAndTo[0], to = FromAndTo[1];
            if (name.isBlank() || from.isBlank() || to.isBlank()) throw new DukeException("Name, From, and To cannot be empty!");
            LocalDateTime fromDateTime = LocalDateTime.parse(from, DATE_DATA_FORMATTER),
                    toDateTime = LocalDateTime.parse(to, DATE_DATA_FORMATTER);
            taskList.addTask(storage, new Event(name, fromDateTime, toDateTime));

        } else {
            throw new DukeException(" " + input + " is a invalid command!");
        }
    }

    public void run(Storage storage, TaskList taskList) {
        String input;

        while(sc.hasNext()) {
            input = sc.nextLine();

            if (input.equals("bye")) break;

            System.out.println(line);
            try {
                doCommand(input, storage, taskList);
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

        System.out.println(line +
                "\n Bye. Hope to see you again soon!\n" + line);
    }

}
