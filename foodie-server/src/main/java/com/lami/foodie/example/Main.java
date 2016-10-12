package com.lami.foodie.example;

import com.lami.foodie.*;
import com.lami.foodie.WriteProxy;
import com.lami.foodie.http.HttpMessageReaderFactory;
import com.lami.foodie.IMessageProcessor;
import com.lami.foodie.Message;
import com.lami.foodie.Server;

import java.io.IOException;

/**
 * Created by jjenkov on 19-10-2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Length: 38\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body>Hello World!</body></html>";

        final byte[] httpResponseBytes = httpResponse.getBytes("UTF-8");

        IMessageProcessor messageProcessor = new IMessageProcessor(){
            public void process(Message message, WriteProxy writeProxy) {
                System.out.println("Message Received from socket: " + message.socketId);

                Message response = writeProxy.getMessage();
                response.socketId = message.socketId;
                response.writeToMessage(httpResponseBytes);

                writeProxy.enqueue(response);
            }

        };

        Server server = new Server(9999, new HttpMessageReaderFactory(), messageProcessor);

        server.start();

    }


}
