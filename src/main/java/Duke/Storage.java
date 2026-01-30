package Duke;

import com.sun.source.util.TaskListener;

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

public class Storage {
    public static final DateTimeFormatter DATE_DATA_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private final String FILEPATH;
    private File file;

    public Storage(String FilePath) throws IOException {
        this.FILEPATH = FilePath;
        this.file = new File(FILEPATH);

        //Tries to create dylan.txt file if do not exit
        file.getParentFile().mkdirs();
        file.createNewFile();

    }

    //Initialize listOfThingsToDo Array using dylan.txt
    //Creates directory if do not exist
    //Create dylan.txt if does not exist
    public ArrayList<Task> loadTasks() throws Exception {
        //Tries to create dylan.txt file if do not exit
        ArrayList<Task> listOfTasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String[] inputTask = sc.nextLine().split(" \\| ");

            //Checks if data input from dylan.txt is valid
            checkDataFileInput(inputTask);

            String taskType = inputTask[0], taskName = inputTask[2];
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
                LocalDateTime from = LocalDateTime.parse(inputTask[3], DATE_DATA_FORMATTER), to =
                        LocalDateTime.parse(inputTask[4], DATE_DATA_FORMATTER);
                Task taskEvent = new Event(taskName, from, to, isTaskDone);
                listOfTasks.add(taskEvent);
                break;
            default:
                throw new DukeException("dylan.txt data file is corrupted");
            }

        }
        return listOfTasks;
    }

    //Checks if data from dylan.txt is valid
    public void checkDataFileInput(String[] input) throws DukeException {
        if (input.length < 3 || input.length > 5) {
            throw new DukeException("dylan.txt data file is corrupted: Length less than 3 or more than 4");
        }

        String taskType = input[0], markAsDone = input[1], taskName = input[2];

        if (!markAsDone.equals("1") && !markAsDone.equals("0")) {
            throw new DukeException("dylan.txt data file is corrupted: markAsDone is invalid");
        }

        if (taskName.isBlank()) {
            throw new DukeException("dylan.txt data file is corrupted: task name is blank");
        }

        if (!taskType.equals("T") && !taskType.equals("D") && !taskType.equals("E")) {
            throw new DukeException("dylan.txt data file is corrupted: Invalid task Type");
        }

        if (taskType.equals("T")) {
            if (input.length != 3) {
                throw new DukeException("dylan.txt data file is corrupted: Wrong number of inputs for Deadline task");
            }
        }

        if (taskType.equals("D")) {
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

        if (taskType.equals("E")) {
            if (input.length != 5) {
                throw new DukeException("dylan.txt data file is corrupted: Wrong number of inputs for Deadline task");
            }

            String from = input[3], to = input[4];
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

    public void updateDataFile(TaskList taskList) {
        ArrayList<Task> listOfTasks = taskList.get();
        try {
            List<String> listOfString = new ArrayList<>();
            for (Task task : listOfTasks) {
                listOfString.add(task.dataInputString());
            }

            Files.write(Paths.get(FILEPATH), listOfString);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("IOException: Failed to update data file");
        }
    }


}
