import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestDiskClassLoader {
    /*
    * 将Test.java文件编译后的Test.class放在D:\lib文件夹下
    * 测试用类加载器DiskClassLoader加载该文件
    * */
    @Test
    public void test1() {
        DiskClassLoader diskClassLoader = new DiskClassLoader("D:\\lib");
        try {
            Class c = diskClassLoader.loadClass("com.jarvi.test.Test");
            if (c != null) {
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say", null);
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException
                         | SecurityException |
                         IllegalArgumentException |InvocationTargetException e) {
                    e.printStackTrace();
                }  catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
