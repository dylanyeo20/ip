package duke;

import command.Command;
import task.TaskList;

/**
 * Main class of Chatbot
 */
public class Dylan {
    private static final String FILEPATH = "./src/main/java/data/dylan.txt";

    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private String commandType;

    /**
     * Constructs a Dylan chatbot instance and initializes UI, storage, and task list.
     *
     * @throws Exception if Storage or TaskList fails to initialize
     */
    public Dylan() {
        try {
            this.ui = new UI();
            this.storage = new Storage(FILEPATH);
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            System.out.println("Exiting: " + e.getMessage());
            System.exit(0);
        }

    }

    private void run() {
        while (true) {
            try {
                String input = ui.get();
                if (input.equals("bye")) {
                    System.out.println(ui.printByeMessage());
                    return;
                }
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
            System.out.println(dylan.ui.printWelcomeMessage());
            dylan.run();
        } catch (Exception e) {
            System.out.println("Exiting: " + e.getMessage());
            System.exit(0);
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
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
        return ui.printWelcomeMessage();
    }

}
