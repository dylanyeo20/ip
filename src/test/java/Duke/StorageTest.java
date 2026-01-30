package Duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {



    @Test
    public void corruptedFileTest() {
        try {
            Storage storage = new Storage("./src/test/java/data/dylan.txt");
            DukeException ex = assertThrows(DukeException.class,
                    () -> storage.loadTasks());
            assertTrue(ex.getMessage().contains("dylan.txt data file is corrupted"));
        } catch (Exception e) {
            System.out.println("Failed to run test: " + e.getMessage());
        }


    }


}
