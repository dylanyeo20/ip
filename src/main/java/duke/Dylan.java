package duke;

import java.util.ArrayList;

import command.Command;
import task.Task;
import task.TaskList;


/**
 * Main class of Chatbot
 */
public class Dylan {
    private static final String FILEPATH = "./src/main/java/data/dylan.txt";

    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private String commandType = "";

    /**
     * Constructs a Dylan chatbot instance and initializes UI, storage, and task list.
     *
     * @throws Exception if Storage or TaskList fails to initialize
     */
    public Dylan() {
        try {
            this.ui = new UI();
            this.storage = new Storage(FILEPATH);

            ArrayList<Task> taskList = storage.loadTasks();
            assert taskList != null : "TaskList is null!";
            tasks = new TaskList(taskList);

        } catch (Exception e) {
            System.out.println("Exiting: " + e.getMessage());
            System.exit(0);
        }

    }

    private void run() {
        while (true) {
            try {
                String input = ui.get();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                System.out.println(command.getString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Dylan dylan = new Dylan();
            System.out.println(dylan.getWelcomeMessage());
            dylan.run();
        } catch (Exception e) {
            System.out.println("Exiting: " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Processes the user's input and returns the command response.
     *
     * <p>This method parses the input into a {@code Command}, executes it,
     * records the command type, and returns the resulting message.
     * If any error occurs during parsing or execution, an error message
     * is returned instead.</p>
     *
     * @param input The raw user input string.
     * @return The response message from the executed command,
     * or an error message if execution fails.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Parser returned null in GetResponse";
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();

            return c.getString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }

    public String getWelcomeMessage() {
        ArrayList<Task> upcomingTasks = tasks.getUpcomingTasks();
        return ui.printWelcomeMessage(upcomingTasks);
    }

}
