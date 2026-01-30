package Duke;

import java.io.IOException;

/**
 * Main class of Chatbot
 */
public class Dylan {
    private static final String FILEPATH = "./src/main/java/data/dylan.txt";

    private UI ui;
    private Storage storage;
    private TaskList listOfThingsToDo;

    /**
     * Constructs a Dylan chatbot instance and initializes UI, storage, and task list.
     * @param filePath Path to the data file used for loading and saving tasks.
     * @throws Exception if Storage or TaskList fails to initialize
     */
    public Dylan(String filePath) throws Exception {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        listOfThingsToDo = new TaskList(storage.loadTasks());
    }

    private void run() {
        ui.printWelcomeMessage();
        ui.run(this.storage, this.listOfThingsToDo);
    }

    public static void main(String[] args) {
        try {
            new Dylan(FILEPATH).run();
        } catch (Exception e) {
            System.out.println("Exiting: " + e.getMessage());
            System.exit(0);
        }
    }
}
