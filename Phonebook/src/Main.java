import java.util.Scanner;

public class Main {
    private static PhoneBook book = new PhoneBook();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Введите номер, имя или команду:");
            String input = sc.nextLine();
            if (input.equals("0")) {
                break;
            }
            if(input.equals("LIST")){
                book.getAllContacts();
            }else{
                book.start(input);
            }
        }
    }
}
