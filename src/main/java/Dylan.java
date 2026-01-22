import java.lang.reflect.Array;
import java.util.*;

public class Dylan {

    public static void print(ArrayList<Task> listOfThingsToDo) {
        System.out.println("Here are the tasks in your list:");
        for(Task t : listOfThingsToDo) {
            t.printTask();
        }
    }

    public static void doCommand(String command, ArrayList<Task> listOfThingsToDo) throws Exception {
        Scanner sc = new Scanner(command);
        String input = sc.next();

        if (input.equals("list")) print(listOfThingsToDo);

        else if (input.equals("mark")){
            if (!sc.hasNextInt()) throw new DukeException(" Please give an index to Mark!");
            int index  = sc.nextInt() - 1;
            if (index < 0 || index >= Task.totalTask()) throw new DukeException(index + " is a invalid index!");
            listOfThingsToDo.get(index).markAsDone();

        }  else if (input.equals("unmark")) {
            if (!sc.hasNextInt()) throw new DukeException(" Please give an index to unMark!");
            int index  = sc.nextInt() - 1;
            if (index < 0 || index >= Task.totalTask()) throw new DukeException(index + " is a invalid index!");
            listOfThingsToDo.get(index).unmarkAsDone();

        } else if (input.equals("todo")){//else we just add new task
            if (!sc.hasNext()) throw new DukeException(" Please give description of task");
            String name = sc.nextLine();
            listOfThingsToDo.add(new ToDos(name));
        } else if (input.equals("deadline")) {
            if (!sc.hasNext()) throw new DukeException(" Please give description and deadline of task");
            String[] nameAndBy = sc.nextLine().split(" /by ");
            if (nameAndBy.length != 2) throw new DukeException("Invalid command! <Description> /by <Deadline>");
            String name = nameAndBy[0].trim(), by = nameAndBy[1].trim();
            if (name.isBlank() || by.isBlank()) throw new DukeException("Name and By cannot be empty!");


            listOfThingsToDo.add(new Deadlines(name, by));
        } else if (input.equals("event")) {
            if (!sc.hasNext()) throw new DukeException(" Please give description, from, to of task");
            String[] nameAndFromAndTo = sc.nextLine().split(" /from ");
            if (nameAndFromAndTo.length != 2) throw new DukeException("Invalid syntax! <Description> /from <from> /to <to>");
            String[] FromAndTo = nameAndFromAndTo[1].split(" /to ");
            if (FromAndTo.length != 2) throw new DukeException("Invalid syntax! <Description> /from <from> /to <to>");
            String name = nameAndFromAndTo[0], from = FromAndTo[0], to = FromAndTo[1];
            if (name.isBlank() || from.isBlank() || to.isBlank()) throw new DukeException("Name, From, and To cannot be empty!");
            listOfThingsToDo.add(new Event(name, from, to));
        } else {
            throw new DukeException(" " + input + " is a invalid command!");
        }
    }

    public static void main(String[] args) {
        String chatbot = "Dylan", line = "____________________________________________________________";
        System.out.println(String.format("____________________________________________________________\n" +
                " Hello! I'm %s\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n", chatbot));

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listOfThingsToDo = new ArrayList<>();

        String input;
        while(sc.hasNext()) {
            input = sc.nextLine();

            if (input.equals("bye")) break;

            System.out.println(line);
            try {
                doCommand(input, listOfThingsToDo);
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            } finally {
                System.out.println(line);
            }
        }

        System.out.println(line +
                "\n Bye. Hope to see you again soon!\n" + line);
    }
}
