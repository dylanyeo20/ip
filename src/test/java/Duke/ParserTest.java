package Duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {

    @Test
    public void invalidCommand(){
        try {
            Storage fakeStorage = new FakeStorage();
            TaskList fakeTaskList = new TaskList(fakeStorage.loadTasks());

            DukeException ex = assertThrows(DukeException.class,
                    () -> Parser.doCommand("todo", fakeStorage, fakeTaskList));

            assertTrue(ex.getMessage().contains(" Please give description of task"));
        } catch (Exception e) {
            System.out.println("Fail to run test: " + e.getMessage());
        }
    }

}

