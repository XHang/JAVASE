package com.cxh.socket.tcp;


import com.cxh.stream.StreamUitl;
import com.sun.xml.internal.ws.util.StreamUtils;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import java.security.Security;

/**
 * 安全套接字连接的服务端
 */
public class SecuritySocketServer {


    public static  int BIND_PORT =3305;
    public static void main(String [] args) throws Exception{
        InputStream storeInputStream = getServerStore();
        SSLServerSocket sslServerSocket =null;
        try {
            sslServerSocket = getSSLServerSocket(BIND_PORT,storeInputStream);
            sslServerSocket.setNeedClientAuth(false);
            Socket socket = sslServerSocket.accept();
            System.out.println("已接受来自客户端的安全连接");
            InputStream inputStream = socket.getInputStream();
            String msg = StreamUitl.inputStreamConversionString(inputStream,"utf-8");
            System.out.println("客户端的信息是："+msg);
            sslServerSocket.close();
        }finally {
            if(sslServerSocket!=null){
                sslServerSocket.close();
            }
            if (storeInputStream!=null){
                storeInputStream.close();
            }
        }
    }

    private static InputStream getServerStore() {
        return SecuritySocketServer.class.getClass().getResourceAsStream("/test_server.jks");
    }


    /**
     * 创建一个安全的SSLSocket
     * @param port 端口号
     * @param serverKeyStore 服务端的keyStore，至少需要包含密钥，对客户端发来的信息进行解密
     * @return
     */
    private static SSLServerSocket getSSLServerSocket(int port, InputStream serverKeyStore) throws Exception {
        //获取jks的keyStore实现
        KeyStore keyStore = KeyStore.getInstance("jks");
        //加载指定的keyStore对象(服务端的密钥库)，指定密码的情况下，会对密钥库的完整性进行校验，如果不指定的话，好像也没事
        keyStore.load(serverKeyStore, "123456".toCharArray());
       // 密钥管理器的工厂,使用默认的算法·构建一个密钥管理器
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        //使用密钥库来初始化账号密钥管理器
        keyManagerFactory.init(keyStore,"123456".toCharArray());
        //返回TLSv1.2协议的SSLContext 对象
        SSLContext context = SSLContext.getInstance("TLSv1.2");
        //使用KeyManagers初始化SSLContext，由于是服务端，所以初始化只需要提供服务端的密钥库即可
        context.init(keyManagerFactory.getKeyManagers(), null, null);
        SSLServerSocketFactory serverSocketFactory = context.getServerSocketFactory();
        return (SSLServerSocket) serverSocketFactory.createServerSocket(port);
    }

}
