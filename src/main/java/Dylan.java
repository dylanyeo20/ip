import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;


public class Dylan {

    public static final String filePath = "./src/main/java/data/dylan.txt";

    public static void print(ArrayList<Task> listOfThingsToDo) {
        System.out.println("Here are the tasks in your list:");
        for(Task t : listOfThingsToDo) {
            t.printTask();
        }
    }

    public static void deleteTask(ArrayList<Task> xs, int index) {
        String taskStatus = xs.get(index).getStatus();
        xs.remove(index);
        Task.reduceTask();
        System.out.println("Noted. I've removed this task: \n" + taskStatus);
        System.out.println("Now you have " + Task.totalTask() + " tasks in the list.");

        //Update index fields in each of the tasks
        for(int i = index; i < xs.size(); i++) {
            Task t = xs.get(i);
            t.reduceIndex();
        }
    }

    public static void doCommand(String command, ArrayList<Task> listOfThingsToDo) throws Exception {
        Scanner sc = new Scanner(command);
        String input = sc.next();

        if (input.equals("list")) {
            print(listOfThingsToDo);
            return;
        }

        else if (input.equals("mark")){
            if (!sc.hasNextInt()) throw new DukeException(" Please give an index of task to Mark!");
            int index  = sc.nextInt() - 1;
            if (index < 0 || index >= Task.totalTask()) throw new DukeException((index + 1) + " is a invalid index!");
            listOfThingsToDo.get(index).markAsDone();


        }  else if (input.equals("unmark")) {
            if (!sc.hasNextInt()) throw new DukeException(" Please give an index of task to unMark!");
            int index = sc.nextInt() - 1;
            if (index < 0 || index >= Task.totalTask()) throw new DukeException((index + 1) + " is a invalid index!");
            listOfThingsToDo.get(index).unmarkAsDone();

        } else if (input.equals("delete")) {
            if (!sc.hasNextInt()) throw new DukeException(" Please give an index of task to delete!");
            int index = sc.nextInt() - 1;
            if (index < 0 || index >= Task.totalTask()) throw new DukeException((index + 1) + " is a invalid index!");

            deleteTask(listOfThingsToDo, index);

        } else if (input.equals("todo")){//else we just add new task
            if (!sc.hasNext()) throw new DukeException(" Please give description of task");
            String name = sc.nextLine();
            listOfThingsToDo.add(new ToDos(name));

        } else if (input.equals("deadline")) {
            if (!sc.hasNext()) throw new DukeException(" Please give description and deadline of task");
            String[] nameAndBy = sc.nextLine().split(" /by ");
            if (nameAndBy.length != 2) throw new DukeException("Invalid command! <Description> /by <Deadline>");
            String name = nameAndBy[0].trim(), by = nameAndBy[1].trim();
            if (name.isBlank() || by.isBlank()) throw new DukeException("Name and By cannot be empty!");


            listOfThingsToDo.add(new Deadlines(name, by));

        } else if (input.equals("event")) {
            if (!sc.hasNext()) throw new DukeException(" Please give description, from, to of task");
            String[] nameAndFromAndTo = sc.nextLine().split(" /from ");
            if (nameAndFromAndTo.length != 2) throw new DukeException("Invalid syntax! <Description> /from <from> /to <to>");
            String[] FromAndTo = nameAndFromAndTo[1].split(" /to ");
            if (FromAndTo.length != 2) throw new DukeException("Invalid syntax! <Description> /from <from> /to <to>");
            String name = nameAndFromAndTo[0], from = FromAndTo[0], to = FromAndTo[1];
            if (name.isBlank() || from.isBlank() || to.isBlank()) throw new DukeException("Name, From, and To cannot be empty!");
            listOfThingsToDo.add(new Event(name, from, to));

        } else {
            throw new DukeException(" " + input + " is a invalid command!");
        }

        updateDataFile(listOfThingsToDo);
    }

    //Checks if data from dylan.txt is valid
    public static void checkDataFileInput(String[] input) throws DukeException{
        if (input.length < 3 || input.length > 5 ) {
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
            if (input.length != 3 ) {
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
        }

        if (taskType.equals("E")) {
            if (input.length != 5) {
                throw new DukeException("dylan.txt data file is corrupted: Wrong number of inputs for Deadline task");
            }

            String from = input[3], to = input[4];
            if (from.isBlank() || to.isBlank()) {
                throw new DukeException("dylan.txt data file is corrupted: Missing From or To  for Event task");
            }
        }
    }

    //Update dylan.txt with latest data
    public static void updateDataFile(ArrayList<Task> listOfTasks) {
        try {
            List<String> listOfString = new ArrayList<>();
            for(Task task : listOfTasks) {
                listOfString.add(task.dataInputString());
            }

            Files.write(Paths.get(filePath), listOfString);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("IOException: Failed to update data file");
        }
    }

    //Initialize listOfThingsToDo Array using dylan.txt
    //Creates directory if do not exist
    //Create dylan.txt if does not exist
    public static void initListOfThingsToDo(ArrayList<Task> listOfTasks) {
        File file = new File(filePath);

        //Tries to create dylan.txt file if do not exit
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();

            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
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
                        String by = inputTask[3];
                        Task taskDeadline = new Deadlines(taskName, by, isTaskDone);
                        listOfTasks.add(taskDeadline);
                        break;
                    case "E":
                        String from = inputTask[3], to = inputTask[4];
                        Task taskEvent = new Event(taskName, from, to, isTaskDone);
                        listOfTasks.add(taskEvent);
                        break;
                    default:
                        throw new DukeException("dylan.txt data file is corrupted");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }






    }



    public static void main(String[] args) {
        String chatbot = "Dylan", line = "____________________________________________________________";
        System.out.println(String.format("____________________________________________________________\n" +
                " Hello! I'm %s\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n", chatbot));

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listOfThingsToDo = new ArrayList<>();

        initListOfThingsToDo(listOfThingsToDo);


        String input;

        while(sc.hasNext()) {
            input = sc.nextLine();

            if (input.equals("bye")) break;

            System.out.println(line);
            try {
                doCommand(input, listOfThingsToDo);
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
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
