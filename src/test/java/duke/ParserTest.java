package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.TaskList;

public class ParserTest {

    @Test
    public void invalidCommand() {
        try {
            Storage fakeStorage = new FakeStorage();
            TaskList fakeTaskList = new TaskList(fakeStorage.loadTasks());
            UI ui = new UI();

            DukeException ex =
                    assertThrows(DukeException.class, () -> Parser.doCommand("todo", fakeStorage, fakeTaskList, ui));

            assertTrue(ex.getMessage().contains(" Please give description of task"));
        } catch (Exception e) {
            System.out.println("Fail to run test: " + e.getMessage());
        }
    }

}

