package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.DukeException;
import task.Deadlines;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDos;


/**
 * Represents the data file.
 * Used for loading and storing data from data file.
 */
public class Storage {

    public static final DateTimeFormatter DATE_DATA_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private final String filePath;
    private File file;


    /**
     * Create instance of Storage class.
     * Creates a new directory and data file if data file not found.
     *
     * @param filePath File path of data file.
     * @throws IOException Throws
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);

        //Tries to create dylan.txt file if it does not exist
        file.getParentFile().mkdirs();
        file.createNewFile();

    }


    /**
     * Loads existing tasks from data file
     *
     * @return ArrayList of tasks current in data file
     * @throws Exception If data file not found or if data is corrupted
     */
    public ArrayList<Task> loadTasks() throws Exception {
        //Tries to create dylan.txt file if it does not exit
        ArrayList<Task> listOfTasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String[] inputTask = sc.nextLine().split(" \\| ");
            checkDataFileInput(inputTask);

            String taskType = inputTask[0];
            String taskName = inputTask[2];
            boolean isTaskDone = Integer.parseInt(inputTask[1]) == 1;

            switch (taskType) {
            case "T":
                Task taskTodo = new ToDos(taskName, isTaskDone);
                listOfTasks.add(taskTodo);
                break;
            case "D":
                LocalDateTime by = LocalDateTime.parse(inputTask[3], DATE_DATA_FORMATTER);
                Task taskDeadline = new Deadlines(taskName, by, isTaskDone);
                listOfTasks.add(taskDeadline);
                break;
            case "E":
                LocalDateTime from = LocalDateTime.parse(inputTask[3], DATE_DATA_FORMATTER);
                LocalDateTime to = LocalDateTime.parse(inputTask[4], DATE_DATA_FORMATTER);
                Task taskEvent = new Event(taskName, from, to, isTaskDone);
                listOfTasks.add(taskEvent);
                break;
            default:
                throw new DukeException("dylan.txt data file is corrupted");
            }
        }
        return listOfTasks;
    }


    /**
     * Checks if data in the data file is valid (not corrupted)
     *
     * @param input Data from the data file
     * @throws DukeException If data is corrupted
     */
    public void checkDataFileInput(String[] input) throws DukeException {
        if (input.length < 3 || input.length > 5) {
            throw new DukeException("dylan.txt data file is corrupted: Length less than 3 or more than 5");
        }

        String taskType = input[0];
        String markAsDone = input[1];
        String taskName = input[2];

        checkInputs(markAsDone, taskName, taskType);

        if (taskType.equals("T")) {
            checkTodoData(input);
        } else if (taskType.equals("D")) {
            checkDeadlineData(input);
        } else if (taskType.equals("E")) {
            checkEventData(input);
        } else {
            throw new DukeException("Unknown error: Data file is corrupted");
        }
    }


    /**
     * Stores all tasks from task list into the data file.
     *
     * @param taskList List of all tasks
     */
    public void updateDataFile(TaskList taskList) throws IOException {
        ArrayList<Task> listOfTasks = taskList.get();

        List<String> listOfString = new ArrayList<>();
        for (Task task : listOfTasks) {
            listOfString.add(task.dataInputString());
        }

        Files.write(Paths.get(filePath), listOfString);
    }

    /**
     * Checks if the three parameters from data file is valid.
     *
     * @param markAsDone
     * @param taskName
     * @param taskType
     * @throws DukeException
     */
    public void checkInputs(String markAsDone, String taskName, String taskType) throws DukeException {
        if (!markAsDone.equals("1") && !markAsDone.equals("0")) {
            throw new DukeException("dylan.txt data file is corrupted: markAsDone is invalid");
        }

        if (taskName.isBlank()) {
            throw new DukeException("dylan.txt data file is corrupted: task name is blank");
        }

        if (!taskType.equals("T") && !taskType.equals("D") && !taskType.equals("E")) {
            throw new DukeException("dylan.txt data file is corrupted: Invalid task Type");
        }
    }

    /**
     * Checks if data is valid for Todo Task.
     *
     * @param input
     * @throws DukeException
     */
    public void checkTodoData(String[] input) throws DukeException {
        if (input.length != 3) {
            throw new DukeException("dylan.txt data file is corrupted: Wrong number of inputs for Todo task");
        }
    }

    /**
     * Checks if data is valid for a Deadline Task
     *
     * @param input
     * @throws DukeException
     */
    public void checkDeadlineData(String[] input) throws DukeException {
        if (input.length != 4) {
            throw new DukeException("dylan.txt data file is corrupted: Wrong number of inputs for Deadline task");
        }

        String by = input[3];
        if (by.isBlank()) {
            throw new DukeException("dylan.txt data file is corrupted: Missing deadline for deadline event");
        }

        try {
            LocalDateTime.parse(by, DATE_DATA_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("dylan.txt data file is corrupted: Invalid Date format");
        }
    }

    /**
     * Checks if data is valid for a Event Task
     *
     * @param input
     * @throws DukeException
     */
    public void checkEventData(String[] input) throws DukeException {
        if (input.length != 5) {
            throw new DukeException("dylan.txt data file is corrupted: Wrong number of inputs for Deadline task");
        }

        String from = input[3];
        String to = input[4];
        if (from.isBlank() || to.isBlank()) {
            throw new DukeException("dylan.txt data file is corrupted: Missing From or To  for Event task");
        }

        try {
            LocalDateTime.parse(from, DATE_DATA_FORMATTER);
            LocalDateTime.parse(to, DATE_DATA_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("dylan.txt data file is corrupted: Invalid Date format");
        }
    }


}
