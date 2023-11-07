import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    //向response中封装request,以便获取request中的请求参数
    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileReader fileReader = null;
        try {
            //读取文件内容
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                //如果文件存在则保存在output中
                fileReader = new FileReader(file);
                StringBuilder fileContent = new StringBuilder();
                int content = -1;
                while ((content = fileReader.read()) != -1) {
                    fileContent.append((char) content);
                }
                System.out.println("file content:");
                System.out.println(fileContent.toString());
                String response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "\r\n" +
                        "<html><body><h1>" + fileContent.toString() + "</h1></body></html>";
                output.write(response.getBytes());
            }else {
                //文件不存在则输出404信息
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }
}
