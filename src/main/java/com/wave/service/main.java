package com.wave.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;

/**
 * Created by ocean on 17-1-12.
 */

public class main {

    public static void main(String[] args) {
        TTransport transport = null;

        try {
            transport = new TSocket("localhost", 9090);
            TProtocol protocol = new TBinaryProtocol(transport);
            FutureService.Client client = new FutureService.Client(protocol);
            transport.open();

            List<News> news = client.futureRelatedNews("hahah");
            System.out.println(news.get(0).related);

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }
    }

}
