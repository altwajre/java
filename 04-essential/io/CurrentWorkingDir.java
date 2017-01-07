import java.net.URL;

public class CurrentWorkingDir {
    public static void main(String[] args) {
        URL location = CurrentWorkingDir.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
    }
}
