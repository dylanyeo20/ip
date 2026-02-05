package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkAsDoneCommand;
import command.UnmarkAsDoneCommand;
import exception.DukeException;
import task.Task;


/**
 * Parses the commands, Checks validity of command and execute them by calling the respective APIs
 */
public class Parser {
    public static final DateTimeFormatter DATE_DATA_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Checks validity of command and execute them using respective APIs.
     *
     * @param command User input from UI
     * @throws Exception If command is invalid
     */
    public static Command parse(String command) throws Exception {
        Scanner sc = new Scanner(command);
        String input = sc.next();

        if (input.isBlank()) {
            throw new DukeException("Invalid Command: null!");
        }

        switch (input) {
        case "list":
            Command c = new ListCommand();
            return c;
        case "mark":
            if (!sc.hasNextInt()) {
                throw new DukeException("Please give an index of task to Mark!");
            }
            int index = sc.nextInt() - 1;
            if (index < 0 || index >= Task.totalTask()) {
                throw new DukeException((index + 1) + " is a invalid index!");
            }
            return new MarkAsDoneCommand(index);

        case "unmark":
            if (!sc.hasNextInt()) {
                throw new DukeException(" Please give an index of task to unMark!");
            }
            int index1 = sc.nextInt() - 1;
            if (index1 < 0 || index1 >= Task.totalTask()) {
                throw new DukeException((index1 + 1) + " is a invalid index!");
            }
            return new UnmarkAsDoneCommand(index1);

        case "delete":
            if (!sc.hasNextInt()) {
                throw new DukeException(" Please give an index of task to delete!");
            }
            int index2 = sc.nextInt() - 1;
            if (index2 < 0 || index2 >= Task.totalTask()) {
                throw new DukeException((index2 + 1) + " is a invalid index!");
            }
            return new DeleteCommand(index2);

        case "todo":
            if (!sc.hasNext()) {
                throw new DukeException(" Please give description of task");
            }
            String name = sc.nextLine();
            if (name.isBlank()) {
                throw new DukeException("Invalid command! Missing Task name");
            }
            return new AddCommand(name);

        case "deadline":
            if (!sc.hasNext()) {
                throw new DukeException(" Please give description and deadline of task");
            }

            String[] nameAndBy = sc.nextLine().split(" /by ");
            if (nameAndBy.length != 2) {
                throw new DukeException("Invalid command! <Description> /by <Deadline>");
            }

            String deadlineName = nameAndBy[0].trim();
            String by = nameAndBy[1].trim();
            if (deadlineName.isBlank() || by.isBlank()) {
                throw new DukeException("Invalid command! Name and By cannot be empty!");
            }
            try {
                LocalDateTime dateTime = LocalDateTime.parse(by, DATE_DATA_FORMATTER);
                return new AddCommand(deadlineName, dateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid syntax! Please input Date & Time in: dd/mm/yyyy HHMM");
            }
        case "event":
            if (!sc.hasNext()) {
                throw new DukeException(" Please give description, from, to of task");
            }
            String[] nameAndfromAndTo = sc.nextLine().split(" /from ");
            if (nameAndfromAndTo.length != 2) {
                throw new DukeException("Invalid syntax! <Description> /from <from> /to <to>");
            }
            String[] fromAndTo = nameAndfromAndTo[1].split(" /to ");
            if (fromAndTo.length != 2) {
                throw new DukeException("Invalid syntax! <Description> /from <from> /to <to>");
            }

            String eventName = nameAndfromAndTo[0];
            String from = fromAndTo[0];
            String to = fromAndTo[1];
            if (eventName.isBlank() || from.isBlank() || to.isBlank()) {
                throw new DukeException("Name, From, and To cannot be empty!");
            }

            try {
                LocalDateTime fromDateTime = LocalDateTime.parse(from, DATE_DATA_FORMATTER);
                LocalDateTime toDateTime = LocalDateTime.parse(to, DATE_DATA_FORMATTER);
                return new AddCommand(eventName, fromDateTime, toDateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid syntax! Please input Date & Time in: dd/mm/yyyy HHMM");
            }
        case "find":
            String findName = sc.nextLine();
            return new FindCommand(findName);

        default:
            throw new DukeException(" " + input + " is a invalid command!");
        }
    }


}
