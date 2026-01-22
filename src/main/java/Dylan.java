import java.lang.reflect.Array;
import java.util.*;

public class Dylan {

    public static void print(ArrayList<Task> listOfThingsToDo) {
        System.out.println("Here are the tasks in your list: ");
        for(Task t : listOfThingsToDo) {
            t.printTask();
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
        while(true) {
            input = sc.nextLine();

            if (input.equals("bye")) break;

            System.out.println(line);
            if (input.equals("list")) print(listOfThingsToDo);
            else {
                String[] inputArray = input.split(" ");
                if (inputArray[0].equals("mark")) {
                    int index  = Integer.parseInt(inputArray[1]) - 1;
                    if (index < 0 || index >= Task.totalTask()) continue;

                    listOfThingsToDo.get(index).markAsDone();
                }  else if (inputArray[0].equals("unmark")) {
                    int index  = Integer.parseInt(inputArray[1]) - 1;
                    if (index < 0 || index >= Task.totalTask()) continue;

                    listOfThingsToDo.get(index).unmarkAsDone();
                } else { //else we just add new task
                    System.out.println("added: " + input);
                    listOfThingsToDo.add(new Task(input));
                }
            }
            System.out.println(line);

        }

        System.out.println(line +
                " Bye. Hope to see you again soon!\n" + line);
    }
}
