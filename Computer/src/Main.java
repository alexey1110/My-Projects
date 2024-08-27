public class Main {
    public static void main(String[] args) {
        Computer comp1 = new Computer("Acer", "name1");
        Processor proc = new Processor(3300,8, "AMD", 0.12);
        RAM ram = new RAM(TypeOfRam.DDR4, 16, 0.37);
        StorageOfInformation storage = new StorageOfInformation(TypeOfStorage.SSD, 1024, 1.29);
        Screen screen = new Screen(21, TypeOfScreen.TN, 1.67);
        Keyboard keyboard = new Keyboard(TypeOfKeyboard.MECHANICAL, ScreenBackLight.YES, 0.54);
        comp1.setProcessor(proc);
        comp1.setRam(ram);
        comp1.setStorage(storage);
        comp1.setScreen(screen);
        comp1.setKeyboard(keyboard);

        Computer  comp2 =new Computer("MSI", "name2",
                new Processor(2700, 6, "Intel", 0.05),
                new RAM(TypeOfRam.DDR5, 32, 0.23),
                new StorageOfInformation(TypeOfStorage.HDD, 512, 0.97),
                new Screen(24, TypeOfScreen.IPS, 2.09),
                new Keyboard(TypeOfKeyboard.MEMBRANE, ScreenBackLight.NO, 0.21)
                );
        System.out.println(comp1.toString());
        System.out.println(comp2.toString());
        System.out.println("Общая масса комьютера " + comp1.getName() + " - " + comp1.getTotalWeight() + "кг.");
        System.out.println("Общая масса комьютера " + comp2.getName() + " - " + comp2.getTotalWeight() + "кг.");
    }
}
