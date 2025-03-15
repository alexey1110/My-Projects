import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    private static int newWidth = 300;
    public static void main(String[] args) {
        String srcFolder = "C:\\Users\\User\\Desktop\\images";
        String dstFolder = "C:\\Users\\User\\Desktop\\imageexit";

        File srcDir = new File(srcFolder);

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(cores);
        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

//        Resize resize = new Resize(files, newWidth, dstFolder, start);
//        resize.start();

        int middle = files.length / 2;

        File[] files1 = new File[middle / 2];
        System.out.println(files1.length);
        System.arraycopy(files, 0, files1, 0, files1.length);
        Resize resize1 = new Resize(files1, newWidth, dstFolder, start);
        resize1.start();

        File[] files2 = new File[middle / 2];
        System.out.println(files2.length);
        System.arraycopy(files, middle / 2, files2, 0, files2.length);
        Resize resize2 = new Resize(files2, newWidth, dstFolder, start);
        resize2.start();

        File[] files3 = new File[middle / 2];
        System.out.println(files3.length);
        System.arraycopy(files, middle, files3, 0, files3.length);
        Resize resize3 = new Resize(files3, newWidth, dstFolder, start);
        resize3.start();

        File[] files4 = new File[middle / 2];
        System.out.println(files4.length);
        System.arraycopy(files, files.length - (middle / 2), files4, 0, files4.length);
        Resize resize4 = new Resize(files4, newWidth, dstFolder, start);
        resize4.start();
    }
}
