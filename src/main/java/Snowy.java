import java.util.Scanner;

public class Snowy {


    private static final String LINE = "-----------------------------------\n";
    private static final String GREETING = LINE
            + "Hello! My name is Snowy \n"
            + "What can I do for you? \n"
            + LINE;

    private static final String ENDING = "Bye! Hope to see you again soon! \n"
            + LINE;

    private static Task[] tasks = new Task[100];

    private static int numOfTasks;

    private static boolean isRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(GREETING);
        while (isRunning) {
            String lastInput = scanner.nextLine();

            int spaceIndex = lastInput.indexOf(" ");

            String command = (spaceIndex == -1 ? lastInput: lastInput.substring(0, spaceIndex)).toLowerCase();

            String description = spaceIndex == -1 ? "": lastInput.substring(spaceIndex + 1);

            switch (command) {
                case "bye":
                    isRunning = false;
                    break;

                case "list":
                    for (int i = 0; i < numOfTasks; i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks[i]);
                    }
                    break;

                case "mark":
                    try {
                        int index = Integer.parseInt(description);
                        tasks[index - 1].markComplete();
                        System.out.println("Nice! I've marked this task as done:\n  "
                                + tasks[index - 1].toString());
                    }catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid index provided. Please try again");
                    }
                    break;

                case "unmark":
                    try {
                        int index = Integer.parseInt(description);
                        tasks[index - 1].markIncomplete();
                        System.out.println("Ok, I've marked this task as not done yet:\n  "
                                + tasks[index - 1].toString());
                    }catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid index provided. Please try again");
                    }
                    break;

                case "todo":
                    tasks[numOfTasks] = new ToDo(description);
                    System.out.println("New todo task added:\n " + tasks[numOfTasks]);
                    numOfTasks++;
                    break;

                case "deadline":
                    int byIndex = description.indexOf(" /by ");
                    String deadlineName = description.substring(0, byIndex);
                    String date = description.substring(byIndex + 5);
                    tasks[numOfTasks] = new Deadline(deadlineName, date);
                    System.out.println("New Deadline task added:\n " + tasks[numOfTasks]);
                    numOfTasks++;
                    break;

                case "event":
                    int fromIndex = description.indexOf(" /from ");
                    int toIndex = description.indexOf(" /to ");

                    String eventName = description.substring(0, fromIndex);
                    String fromDate = description.substring(fromIndex + 7, toIndex);
                    String toDate = description.substring(toIndex + 5);
                    tasks[numOfTasks] = new Event(eventName, fromDate, toDate);
                    System.out.println("New Event task added:\n " + tasks[numOfTasks]);
                    numOfTasks++;
                    break;


                default:
                    System.out.println("Command not recognized. Please try again");
                    break;
            }

            System.out.print(LINE);

        }


        System.out.print(ENDING);
    }
}
