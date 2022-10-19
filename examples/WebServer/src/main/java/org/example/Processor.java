package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws IOException {
        String req = request.toString();
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        String fileName = "/Users/almicke/Downloads/dc-course-master/test.txt";

        if (req.contains("create")) {
            String name = "";
            int pos = 12;
            while (req.charAt(pos) != ' ') {
                char a = req.charAt(pos);
                name += a;
                pos++;
            }
            File myFile = new File(name);

            if (myFile.createNewFile()) {
                output.println("HTTP/1.1 200 OK");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                output.println("<html>");
                output.println("<body><p> File created: " + name + "</p><body>");
                output.println("</html>");
            } else {
                output.println("<html>");
                output.println("<body><p>" + name + "already exists.</p><body>");
                output.println("</html>");
            }
        }

        else if(req.contains("delete")) {
            File file = new File(fileName);
            if (file.delete()) {
                output.println(file.getName() + " is deleted!");
            } else {
                output.println("Sorry, unable to delete the file.");
            }
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>WELCOME</title></head>");
        output.println("<body><p>WELCOME PAGE</p></body>");
        output.println("</html>");
        output.flush();
        socket.close();
    }
}