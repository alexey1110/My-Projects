import java.util.ArrayList;

public class TodoList {
    ArrayList<String> list = new ArrayList<>();

    public void add(String todo) {
        list.add(todo);
        System.out.println("Добавлено дело \"" + todo + "\"");
    }

    public void add(int index, String todo) {

        if(index>list.size()){
            list.add(todo);
            System.out.println("Добавлено дело \"" + todo + "\"");
        }else{
            list.add(index, todo);
            System.out.println("Добавлено дело \"" + todo + "\"");
        }
    }

    public void edit(int index, String todo) {
        if(index < list.size()){
            System.out.println("Дело \"" + list.get(index) + "\" заменено на \"" + todo + "\"");
            list.set(index, todo);
        }else{
            System.out.println("Дело с таким номером не существует");
        }
    }

    public void delete(int index) {
        if(index < list.size()){
            list.remove(index);
            System.out.println("Дело \"" + list.get(index) + "\" " + "удалено");
        } else{
            System.out.println("Дело с таким номером не существует");
        }

    }

    public ArrayList<String> getTodos() {
        for(int i = 0; i < list.size(); i++){
            System.out.println(i + " - " + list.get(i));
        }
        return list;
    }

}
