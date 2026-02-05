package duke;

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
     *
     * @throws Exception if Storage or TaskList fails to initialize
     */
    public Dylan() {
        try {
            this.ui = new UI();
            this.storage = new Storage(FILEPATH);
            listOfThingsToDo = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            System.out.println("Exiting: " + e.getMessage());
            System.exit(0);
        }

    }

    private void run() {
        ui.printWelcomeMessage();
        ui.run(this.storage, this.listOfThingsToDo);
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
        /*try {
            new Dylan(FILEPATH).run();
        } catch (Exception e) {
            System.out.println("Exiting: " + e.getMessage());
            System.exit(0);
        }*/
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
