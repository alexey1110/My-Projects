import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String request = sc.nextLine();
            if (request.equals("0")) {
                sc.close();
                break;
            }
            //List<String> array = new ArrayList<>(List.of(request.split(" ")));
            String[] string = request.split(" ");
            switch (string[0]){
                case "ADD":
                    if (string.length > 2 && isNumeric(string[1])) {
                        String task = String.join(" ", Arrays.copyOfRange(string, 2, string.length));
                        todoList.add(Integer.parseInt(string[1]), task);
                    }else{
                        String task = String.join(" ", Arrays.copyOfRange(string, 1, string.length));
                        todoList.add(task);
                    }
                    break;
                case "LIST":
                    todoList.getTodos();
                    break;
                case "DELETE":
                    todoList.delete(Integer.parseInt(string[1]));
                    break;
                case "EDIT":
                    String task = String.join(" ", Arrays.copyOfRange(string, 2, string.length));;
                    todoList.edit(Integer.parseInt(string[1]), task);
                    break;
            }
            // TODO:напишите ваш код тут, результат вывести в консоль.
        }

    }
}
