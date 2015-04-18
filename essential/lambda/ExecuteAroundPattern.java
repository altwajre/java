import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ExecuteAroundPattern {
    static String path = "data.txt";

    public static String processFileLimited() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            return br.readLine();  // this line does the useful work
        }
    }

    interface IProcessor{
        String process(BufferedReader reader) throws IOException;
    }

    static class TwoLineProcess implements IProcessor{

        @Override
        public String process(BufferedReader reader) throws IOException {
            return reader.readLine() + reader.readLine();
        }
    }

    public static String processFile(IProcessor processor) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            return processor.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        URL location = ExecuteAroundPattern.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());

        System.out.println("#processFileLimited");
        System.out.println(processFileLimited());

        System.out.println("#processFile(new TwoLineProcess())");
        System.out.println(processFile(new TwoLineProcess()));

        System.out.println("#lambda one line");
        System.out.println(processFile((BufferedReader reader) -> reader.readLine()));

        System.out.println("#lambda two lines");
        System.out.println(processFile((BufferedReader reader) -> reader.readLine() + reader.readLine()));
    }
}
