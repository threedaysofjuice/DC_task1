package org.example;

import static java.lang.Math.pow;

public class Functions {

    public static String createFileName(String request){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 0;
        String s = request.split("create/")[1];
        String filename = "";
        while (s.charAt(i) != ' ' && s.charAt(i) != '\n'){
            filename += s.charAt(i);
            i++;
        }
        return filename;
    }

    public static String deleteFileName(String request){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 0;
        String s = request.split("delete/")[1];
        String filename = "";
        while (s.charAt(i) != ' ' && s.charAt(i) != '\n'){
            filename += s.charAt(i);
            i++;
        }
        return filename;
    }
    public static double[] square(String request){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 0;
        String s = request.split("square/")[1];
        String num = "";
        while (s.charAt(i) != ' ' && s.charAt(i) != '\n'){
            num += s.charAt(i);
            i++;
        }
        double[] res= new double[2];
        res[0] = Double.parseDouble(num);
        res[1] = pow(Double.parseDouble(num), 2);
        return res;
    }
}