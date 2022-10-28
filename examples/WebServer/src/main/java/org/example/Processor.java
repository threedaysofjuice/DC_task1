package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Processor of HTTP request.
 */
public class Processor extends Thread {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }


    //variables...
    //...
  //  @Override
 //   public void run() {
 //       String resp = "";
   //     try {
//
  //          process();
//
  //      } catch (IOException e) {
    //        throw new RuntimeException(e);
      //  }
 //   }

    public void process() throws IOException {
        String req = request.toString();
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        String filename = "";
        String resp = "";
        if (req.contains("create/")){
            filename = System.getProperty("user.dir")+"/files/"+Functions.createFileName(req)+".txt";
            System.out.println("FILENAME TO CREATE IS " + filename);

            try {
                File f = new File(filename);
                if (f.createNewFile()) {
                    resp += "Successfully created file: " + f.getName();
                } else {
                    resp += "File " + f.getName() + " already exists:";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(req.contains("delete/")){
            filename = System.getProperty("user.dir")+"/files/"+Functions.deleteFileName(req)+".txt";
            System.out.println("FILENAME TO DELET IS " + filename);
            try
            {
                File f= new File(filename);
                if(f.delete()){
                    resp += f.getName() + " file successfully deleted";   //getting and printing the file name
                }
                else{
                    resp += "Cannot find file:";

                }
            }
            catch(Exception e){e.printStackTrace();}
        }
        else if(req.contains("square/")){
            double[] res = Functions.square(req);
            resp += "Square of of " + res[0] + " is: " + res[1];
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

