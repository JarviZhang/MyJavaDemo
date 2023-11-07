import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Test1 {
    @Test
    public void test1() throws IOException {
        File classPath = new File(Constants.WEB_ROOT);
        System.out.println("-------------");
        System.out.println(classPath.toString());
        System.out.println("--------------");
        System.out.println(classPath.getCanonicalPath());
        String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
        System.out.println("-----------------");
        System.out.println(repository);
    }
}
