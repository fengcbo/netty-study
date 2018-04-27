package com.shiluns.netty.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author fengchuanbo
 */
public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost",8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        try {
            transport.open();
            Person person = client.getPersonByUserName("zhangsan");
            System.out.println(person);

            Person newPerson = new Person().setName("wangwu").setAge(54).setMarried(true);
            client.savePerson(newPerson);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            transport.close();
        }
    }
}
