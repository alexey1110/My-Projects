import java.util.*;

public class PhoneBook {

    HashMap<String, String> map = new HashMap<>();

    public void start(String str){
        Scanner sc = new Scanner(System.in);
        if(isNumber(str)){
            if (map.containsValue(str)) {
                System.out.println(getContactByPhone(str));
            }else{
                System.out.println("Такого номера нет в телефонной книге.");
                System.out.println("Введите имя абонента для номера \"" + str + "\":");
                String input = sc.nextLine();
                addContact(str, input);
            }
        } else if(isName(str)){
            if  (map.containsKey(str)){
                System.out.println(getContactByName(str));
            }else{
                System.out.println("Такого имени в телефонной книге нет.");
                System.out.println("Введите номер телефона для абонента \"" + str + "\":");
                String input = sc.nextLine();
                addContact(input, str);
            }
        } else {
            System.out.println("Неверный формат ввода");
        }
    }
    public static boolean isNumber(String str){
        String regex = "79[0-9]{9}";
        return str.matches(regex);
    }
    public static boolean isName(String str){
        String regex = "[А-Я]{1}[a-я]+";
        return str.matches(regex);
    }
    public void addContact(String phone, String name) {
        if(phone.trim().isEmpty() || name.trim().isEmpty() || !isName(name) || !isNumber(phone)){
            return;
        }
        String removename = null;
        for(Map.Entry<String, String> entry : map.entrySet()){
            if(entry.getValue().equals(phone)){
                removename = entry.getKey();
            }
            if(entry.getKey().equals(name)){
                map.put(name, entry.getValue() + ", " + phone);
                return;
            }
        }
        map.remove(removename);
        map.put(name, phone);
        System.out.println("Контакт сохранён!");
        // проверьте корректность формата имени и телефона
        // (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
        // если такой номер уже есть в списке, то перезаписать имя абонента
    }

    public String getContactByPhone(String phone) {
        for(Map.Entry<String, String> entry : map.entrySet()){
            if(entry.getValue().equals(phone)){
                return entry.getKey() + " - " + entry.getValue();
            }
        }
        return "";
    }

    public Set<String> getContactByName(String name) {
        Set<String> set = new TreeSet<>();
        set.add(name + " - " + map.get(name));
        return set;
    }

    public Set<String> getAllContacts() {
        TreeSet<String> set = new TreeSet<>();
        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue());
            set.add(entry.getKey() + " - " + entry.getValue());
        }
        return set;
    }
    // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
    // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
}