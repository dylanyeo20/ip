package Duke;

public class Dylan {
    private static final String FILEPATH = "./src/main/java/data/dylan.txt";

    private UI ui;
    private Storage storage;
    private TaskList listOfThingsToDo;

    public Dylan(String filePath) {
        this.ui = new UI();
        ui.printWelcomeMessage();
        try {
            this.storage = new Storage(filePath);
            listOfThingsToDo = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            System.out.println("Exiting: " + e.getMessage());
            System.exit(0);
        }
    }

    private void run() {
        ui.run(this.storage, this.listOfThingsToDo);
    }

    public static void main(String[] args) {
        new Dylan(FILEPATH).run();
    }
}
