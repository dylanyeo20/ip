import java.lang.reflect.Array;
import java.util.*;

public class Dylan {
    public static void print(ArrayList<String> xs) {
        int i = 1;
        for(String s : xs) {
            System.out.println(i++ + ". " + s);
        }
    }
    public static void main(String[] args) {
        String chatbot = "Dylan", line = "____________________________________________________________\n";
        System.out.println(String.format("____________________________________________________________\n" +
                " Hello! I'm %s\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n", chatbot));

        Scanner sc = new Scanner(System.in);
        ArrayList<String> listOfThingsToDo = new ArrayList<>();


        String input;
        while(true) {
            input = sc.nextLine();

            if (input.equals("bye")) break;

            System.out.println(line);
            if (input.equals("list")) print(listOfThingsToDo);
            else {
                System.out.println("added: " + input + "\n");
                listOfThingsToDo.add(input);
            }
            System.out.println(line);



        }


        System.out.println(line +
                " Bye. Hope to see you again soon!\n" + line);


    }
}
