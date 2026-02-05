package duke;

import java.io.IOException;

/**
 * Fake storage class, simplified version of Storage class
 */
public class FakeStorage extends Storage {
    public FakeStorage() throws IOException {
        super("./src/main/java/data/dylan.txt");
    }

    @Override
    public void updateDataFile(TaskList taskList) {
        return;
    }
}
