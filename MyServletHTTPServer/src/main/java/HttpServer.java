import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    //存放静态资源的位置:当前项目的webroot文件夹下
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "MyStaticHTTPServer" + File.separator +"webroot";
    //关闭Server的请求
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    //是否关闭Server
    private boolean shutdown = false;


    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }

    private void await() {
        //启动ServerSocket
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //循环等待Request请求
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                //接收到请求
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                //封装input到Request并处理
                Request request = new Request(input);
                request.parse();

                //封装output到response
                Response response = new Response(output);
                response.setRequest(request);
                //不再由Response自己处理
                //response.sendStaticResource();
                //而是如果以/servlet/开头,则委托ServletProcessor来处理
                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request, response);
                } else {
                    //否则让原有的静态资源处理
                    //TODO:COM
//                    StaticResourceProcess process = new StaticResourceProcess();
//                    process.process(request, response);
                }
                //关闭socket
                socket.close();

                //如果是关闭请求,则设置关闭监听request的标志
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
