package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Работа по протоколу TCP
 * Клиент отправляет байт (число) серверу,
 * затем получает квадрат числа
 */
public class SqClient {
    public static void main(String[] args) throws IOException {
        ArrayList<String> people = new ArrayList<String>();
        people.add("Ivan");
        people.add("Ivan");
        people.add("Nikita");
        Set<String> uniqIvan = new HashSet<String>(people);
        String s = uniqIvan.toString();
        System.out.println("Введёная строка: " + s);
        try(Socket socket = new Socket("localhost", 1020)){
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes("UTF-8"));
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();

            byte[] buffer = new byte[1024];
            for (int length; (length = inputStream.read(buffer)) != -1; ) {
                String str = new String(buffer,0,length);
                System.out.println("Response: "+str);
                break;
            }
        }
    }
}
