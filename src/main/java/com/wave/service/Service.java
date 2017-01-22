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
public class Service {
    public static List<News> futureRelatedNews(String future) {
        TTransport transport = null;
        List<News> news = null;

        try {
            transport = new TSocket("10.60.42.202", 8888);
            TProtocol protocol = new TBinaryProtocol(transport);
            FutureService.Client client = new FutureService.Client(protocol);
            transport.open();

            news = client.futureRelatedNews(future);

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
            return news;
        }
    }

    public static List<Price> predictPrice(String future) {
        TTransport transport = null;
        List<Price> prices = null;

        try {
//            transport = new TSocket("10.60.42.202", 8888);
            transport = new TSocket("192.168.1.109", 8888);
            TProtocol protocol = new TBinaryProtocol(transport);
            FutureService.Client client = new FutureService.Client(protocol);
            transport.open();

            prices = client.predictPrice(future, (short) 1);

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
            return prices;
        }
    }
}
