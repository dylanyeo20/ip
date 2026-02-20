**Prompt:**
Chatgpt 5.2
Provide java documentation for this function.
Keep it short and easy to understand for other coders reading this code.

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

**Output:**
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

**Comments:**
Help me quickly provide documentation for the above code, which is
easily understandable by other coders.

**Prompt**
How do we add a title to the windows of my app?

**output**
Option 1 (Recommended): Set title in your main Application class
Find your class that extends Application (usually Launcher, Main, or Duke).
Example:
@Override
public void start(Stage stage) throws IOException {
FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"));
Scene scene = new Scene(fxmlLoader.load());

    stage.setTitle("Dylan Assistant");  // ⭐ SET TITLE HERE
    stage.setScene(scene);
    stage.show();

}
This is the proper JavaFX way
Clean separation of UI and window config
