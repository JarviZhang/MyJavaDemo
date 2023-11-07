import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
//TODO:COM
public class ServletProcessor1 {
    public void process(Request request, Response response) {
        //获取servlet的名字
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/" + 1));

        //初始化URLClassLoader
        URLClassLoader loader = null;
        try {
            //create a URLClassLoader
            URL[] urls = new URL[1];
            //用于自定义特定URL协议的处理方式
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //用classloader加载上面的servlet
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //将加载到的class转成Servlet,并调用service方法处理
//        Servlet servlet = null;
//        try {
//            servlet = (Servlet) myClass.newInstance();
//            servlet.service();
//        }
    }
}
