Java学习过程的各种demo

# [类加载器](MyClassLoader)

实现了一个可以从指定文件位置读取指定名称类文件的类加载器

[测试地址](MyClassLoader/src/test/java/TestDiskClassLoader.java)


#  [针对静态资源的HTTP服务器](MyStaticHTTPServer)

实现了一个可以获取服务器静态资源的HTTP服务器

项目启动地址在[HttpServer](MyStaticHTTPServer/src/main/java/HttpServer.java)类的main方法

启动后访问`http://localhost:8080/文件名`来获取 [MyStaticHTTPServer/webroot](MyStaticHTTPServer/webroot)目录下的对应的文件的内容
