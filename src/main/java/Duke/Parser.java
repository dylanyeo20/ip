package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parser {
    public static final DateTimeFormatter DATE_DATA_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static void doCommand(String command, Storage storage, TaskList taskList) throws Exception {
        Scanner sc = new Scanner(command);
        String input = sc.next();

        if (input.isBlank()) {
            throw new DukeException("Invalid Command: null!");
        }

        switch (input) {
        case "mark":
            if (!sc.hasNextInt()) {
                throw new DukeException(" Please give an index of task to Mark!");
            }
            int index = sc.nextInt() - 1;
            if (index < 0 || index >= Task.totalTask()) {
                throw new DukeException((index + 1) + " is a invalid index!");
            }
            taskList.markAsDone(storage, index);
            break;

        case "unmark":
            if (!sc.hasNextInt()) {
                throw new DukeException(" Please give an index of task to unMark!");
            }
            int index1 = sc.nextInt() - 1;
            if (index1 < 0 || index1 >= Task.totalTask()) {
                throw new DukeException((index1 + 1) + " is a invalid index!");
            }
            taskList.unmarkAsDone(storage, index1);
            break;

        case "delete":
            if (!sc.hasNextInt()) {
                throw new DukeException(" Please give an index of task to delete!");
            }
            int index2 = sc.nextInt() - 1;
            if (index2 < 0 || index2 >= Task.totalTask()) {
                throw new DukeException((index2 + 1) + " is a invalid index!");
            }
            taskList.deleteTask(storage, index2);
            break;

        case "todo":
            if (!sc.hasNext()) {
                throw new DukeException(" Please give description of task");
            }
            String name = sc.nextLine();
            if (name.isBlank()) {
                throw new DukeException("Invalid command! Missing Task name");
            }
            taskList.addTask(storage, new ToDos(name));
            break;

        case "deadline":
            if (!sc.hasNext()) {
                throw new DukeException(" Please give description and deadline of task");
            }

            String[] nameAndBy = sc.nextLine().split(" /by ");
            if (nameAndBy.length != 2) {
                throw new DukeException("Invalid command! <Description> /by <Deadline>");
            }

            String nameq = nameAndBy[0].trim(), by = nameAndBy[1].trim();
            if (nameq.isBlank() || by.isBlank()) {
                throw new DukeException("Name and By cannot be empty!");
            }

            LocalDateTime dateTime = LocalDateTime.parse(by, DATE_DATA_FORMATTER);
            taskList.addTask(storage, new Deadlines(nameq, dateTime));
            break;
        case "event":
            if (!sc.hasNext()) {
                throw new DukeException(" Please give description, from, to of task");
            }

            String[] nameAndFromAndTo = sc.nextLine().split(" /from ");
            if (nameAndFromAndTo.length != 2) {
                throw new DukeException("Invalid syntax! <Description> /from <from> /to <to>");
            }

            String[] FromAndTo = nameAndFromAndTo[1].split(" /to ");
            if (FromAndTo.length != 2) {
                throw new DukeException("Invalid syntax! <Description> /from <from> /to <to>");
            }

            String name1 = nameAndFromAndTo[0], from = FromAndTo[0], to = FromAndTo[1];
            if (name1.isBlank() || from.isBlank() || to.isBlank()) {
                throw new DukeException("Name, From, and To cannot be empty!");
            }

            LocalDateTime fromDateTime = LocalDateTime.parse(from, DATE_DATA_FORMATTER), toDateTime =
                    LocalDateTime.parse(to, DATE_DATA_FORMATTER);
            taskList.addTask(storage, new Event(name1, fromDateTime, toDateTime));
            break;

        default:
            throw new DukeException(" " + input + " is a invalid command!");
        }
    }

}
