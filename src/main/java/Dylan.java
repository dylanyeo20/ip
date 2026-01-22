import java.util.Scanner;

public class Dylan {
    public static void main(String[] args) {
        String chatbot = "Dylan", line = "____________________________________________________________\n";
        System.out.println(String.format("____________________________________________________________\n" +
                " Hello! I'm %s\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n", chatbot));

        Scanner sc = new Scanner(System.in);

        String input = "";
        while(!input.equals("bye")) {
            input = sc.nextLine();
            System.out.println(line + input + "\n" + line);
        }


        System.out.println(
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");


    }
}
