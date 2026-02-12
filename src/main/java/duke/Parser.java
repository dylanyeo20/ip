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
        case "bye":
            System.exit(0);
            return null;
        case "list":
            return new ListCommand();
        case "mark":
            return createMarkCommand(sc);
        case "unmark":
            return createUnmarkCommand(sc);
        case "delete":
            return createDeleteCommand(sc);
        case "todo":
            return createAddTodoCommand(sc);
        case "deadline":
            return createAddDeadlineCommand(sc);
        case "event":
            return createAddEventCommand(sc);
        case "find":
            return createFindCommand(sc);
        default:
            throw new DukeException(" " + input + " is a invalid command!");
        }
    }

    /**
     * Checks if Mark command has a valid index and creates a MarkAsDoneCommand
     *
     * @param sc
     * @return MarkAsDoneCommand if command is valid
     * @throws DukeException
     */
    public static Command createMarkCommand(Scanner sc) throws DukeException {
        if (!sc.hasNextInt()) {
            throw new DukeException("Please give an index of task to Mark!");
        }

        int index = sc.nextInt() - 1;
        if (index < 0 || index >= Task.totalTask()) {
            throw new DukeException((index + 1) + " is a invalid index!");
        }

        return new MarkAsDoneCommand(index);
    }

    /**
     * Checks if Unmark command has a valid index and creates a UnmarkAsDoneCommand
     *
     * @param sc
     * @return UnmarkAsDoneCommand if command is valid
     * @throws DukeException
     */
    public static Command createUnmarkCommand(Scanner sc) throws DukeException {
        if (!sc.hasNextInt()) {
            throw new DukeException(" Please give an index of task to unMark!");
        }

        int index = sc.nextInt() - 1;
        if (index < 0 || index >= Task.totalTask()) {
            throw new DukeException((index + 1) + " is a invalid index!");
        }

        return new UnmarkAsDoneCommand(index);
    }

    /**
     * Checks if delete command has valid index and creates new DeleteCommand Object
     *
     * @param sc
     * @return DeleteCommand object
     * @throws DukeException
     */
    public static Command createDeleteCommand(Scanner sc) throws DukeException {
        if (!sc.hasNextInt()) {
            throw new DukeException(" Please give an index of task to delete!");
        }

        int index = sc.nextInt() - 1;
        if (index < 0 || index >= Task.totalTask()) {
            throw new DukeException((index + 1) + " is a invalid index!");
        }
        return new DeleteCommand(index);
    }

    /**
     * Checks if command syntax is valid, and creates a AddCommand for Todo Task
     *
     * @param sc
     * @return AddCommand
     * @throws DukeException
     */
    public static Command createAddTodoCommand(Scanner sc) throws DukeException {
        if (!sc.hasNext()) {
            throw new DukeException(" Please give description of task");
        }

        String name = sc.nextLine();
        if (name.isBlank()) {
            throw new DukeException("Invalid command! Missing Task name");
        }

        return new AddCommand(name);
    }

    /**
     * Checks if add command syntax is correct for a deadline task.
     *
     * @param sc
     * @return AddCommand for deadline task
     * @throws DukeException
     */
    public static Command createAddDeadlineCommand(Scanner sc) throws DukeException {
        if (!sc.hasNext()) {
            throw new DukeException(" Please give description and deadline of task");
        }

        //Checks if format is correct
        String[] nameAndBy = sc.nextLine().split(" /by ");
        if (nameAndBy.length != 2) {
            throw new DukeException("Invalid command! <Description> /by <Deadline>");
        }

        //Checks if Name and By date was stated
        String deadlineName = nameAndBy[0].trim();
        String by = nameAndBy[1].trim();
        if (deadlineName.isBlank() || by.isBlank()) {
            throw new DukeException("Invalid command! Name and By cannot be empty!");
        }

        try {
            //Checks if format of date is correct
            LocalDateTime dateTime = LocalDateTime.parse(by, DATE_DATA_FORMATTER);
            return new AddCommand(deadlineName, dateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid syntax! Please input Date & Time in: dd/mm/yyyy HHMM");
        }
    }

    /**
     * Checks if add command syntax is correct for an event task.
     *
     * @param sc
     * @return AddCommand for event task
     * @throws DukeException
     */
    public static Command createAddEventCommand(Scanner sc) throws DukeException {
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
    }

    /**
     * Creates and return a findCommand Object
     *
     * @param sc
     * @return FindComand Object
     */
    public static Command createFindCommand(Scanner sc) {
        String findName = sc.nextLine();
        return new FindCommand(findName);
    }


}
