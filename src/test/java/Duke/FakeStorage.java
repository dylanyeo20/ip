package Duke;

import java.io.IOException;

public class FakeStorage extends Storage{
    public FakeStorage() throws IOException {
        super("./src/main/java/data/dylan.txt");
    }
    @Override
    public void updateDataFile(TaskList taskList) {
        return;
    }
}
