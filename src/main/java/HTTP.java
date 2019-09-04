import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
*  HTTP Client implementation
*  Followed by RFC-7230
*
* */



public class HTTP {

    public static void main(String[] args) {

        Socket socket = null;
        OutputStream outputStream;
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        {
            try {
                socket = new Socket("google.no", 80);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream = socket.getOutputStream();
                    inputStream = socket.getInputStream();
                    outputStream.write("GET /index.html HTTP/1.1\r\n".getBytes());
                    outputStream.write("Host: google.no\r\n".getBytes());
                    outputStream.write("Connection: close\r\n".getBytes());
                    outputStream.write("\r\n".getBytes());
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        int i;
                        while ((i = inputStream.read()) != -1) {
                            builder.append((char)i);
                        }
                    } catch (IOException e) {
                        System.out.println("cant read socket");
                    } finally {
                        System.out.println(builder.toString());
                    }

                }

            }
        }
    }

}
