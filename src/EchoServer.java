import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

    public class EchoServer {
        public static final int port = 6379;
        private static final int TIMEOUT = 30;

        public static void main(String[] args) {
            new EchoServer().handle();
        }

        public static String ridicule(String in) {
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (char c : in.toCharArray()) {
                sb.append(random.nextBoolean() ? c : Character.toUpperCase(c));
            }
            return sb.toString();
        }

        public void handle() {
            ServerSocket serverSocket;
            // TODO implement me :)
            try {
                serverSocket = new ServerSocket();
                serverSocket.setSoTimeout(TIMEOUT * 1000);
                serverSocket.bind(new InetSocketAddress(port));
                while (true) {
                    Socket conn = serverSocket.accept();
                    EchoConnection echoConnection = new EchoConnection(conn);
                    echoConnection.run();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        class EchoConnection {
            private Socket socket;
            private PrintWriter writer;
            private BufferedReader reader;
            private boolean connection_alive;

            public EchoConnection(Socket socket) throws IOException {
                this.socket = socket;
                this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.writer = new PrintWriter(socket.getOutputStream());
            }

            public Socket getSocket() {
                return socket;
            }

            public void setSocket(Socket socket) {
                this.socket = socket;
            }

            public PrintWriter getWriter() {
                return writer;
            }

            public void setWriter(PrintWriter writer) {
                this.writer = writer;
            }

            public BufferedReader getReader() {
                return reader;
            }

            public void setReader(BufferedReader reader) {
                this.reader = reader;
            }

            public boolean isConnection_alive() {
                return connection_alive;
            }

            public void setConnection_alive(boolean connection_alive) {
                this.connection_alive = connection_alive;
            }

            public void close() {
                // TODO implement me :)
                connection_alive = false;
            }

            public void run() throws IOException {
                connection_alive = true;
                while (connection_alive) {
                    // TODO implement me :)
                    String sc = reader.readLine();
                        writer.println(ridicule(sc));
                        writer.flush();


                }
                close();
            }
        }
    }


