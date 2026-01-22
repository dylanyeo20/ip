import java.lang.reflect.Array;
import java.util.*;

public class Dylan {

    public static void print(ArrayList<Task> listOfThingsToDo) {
        System.out.println("Here are the tasks in your list:");
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
        while(sc.hasNext()) {
            input = sc.next();

            if (input.equals("bye")) break;

            System.out.println(line);
            if (input.equals("list")) print(listOfThingsToDo);

            else if (input.equals("mark")){
                int index  = sc.nextInt() - 1;
                if (index < 0 || index >= Task.totalTask()) continue;
                listOfThingsToDo.get(index).markAsDone();

            }  else if (input.equals("unmark")) {
                int index  = sc.nextInt() - 1;
                if (index < 0 || index >= Task.totalTask()) continue;
                listOfThingsToDo.get(index).unmarkAsDone();

            } else if (input.equals("todo")){//else we just add new task
                String name = sc.nextLine();
                listOfThingsToDo.add(new ToDos(name));
            } else if (input.equals("deadline")) {
                String[] nameAndBy = sc.nextLine().split("/");
                String name = nameAndBy[0], by = nameAndBy[1].substring(2);


                listOfThingsToDo.add(new Deadlines(name, by));
            } else if (input.equals("event")) {
                String[] nameAndFromAndTo = sc.nextLine().split("/");
                String name = nameAndFromAndTo[0], from = nameAndFromAndTo[1].substring(4), to = nameAndFromAndTo[2].substring(2);
                listOfThingsToDo.add(new Event(name, from, to));
            }
            System.out.println(line);

        }

        System.out.println(line +
                "\n Bye. Hope to see you again soon!\n" + line);
    }
}
