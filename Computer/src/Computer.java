public class Computer {
    private Processor processor;
    private RAM ram;
    private Screen screen;
    private Keyboard keyboard;
    private StorageOfInformation storage;
    private final String vendor;

    public String getName() {
        return name;
    }

    private final String name;

    public Computer(String vendor, String name){
        this.vendor = vendor;
        this.name = name;
    }
    public Computer(String vendor, String name,
                    Processor processor,
                    RAM ram,
                    StorageOfInformation storage,
                    Screen screen,
                    Keyboard keyboard
    ){
        this(vendor, name);
        this.processor = processor;
        this.ram = ram;
        this.screen = screen;
        this.storage = storage;
        this.keyboard = keyboard;
    }
    public Processor getProcessor(){
        return processor;
    }
    public void setProcessor(Processor processor){
        this.processor = processor;
    }
    public RAM getRam() {
        return ram;
    }
    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public StorageOfInformation getStorage() {
        return storage;
    }

    public void setStorage(StorageOfInformation storage) {
        this.storage = storage;
    }
    public void setScreen(Screen screen){
        this.screen = screen;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public double getTotalWeight(){
        return processor.getWeight() + ram.getWeight() + storage.getWeight() + screen.getWeight() + keyboard.getWeight();
    }

    public String toString(){
        return "\nПроизводитель: " + vendor +
                "\nИмя: " + name +
                "\nПроцессор: " + processor.toString() +
                "\nОперативная память: " + ram.toString() +
                "\nНакопитель информации: " + storage.toString() +
                "\nЭкран: " + screen.toString() +
                "\nКлавиатура: " + keyboard.toString();




    }

}
