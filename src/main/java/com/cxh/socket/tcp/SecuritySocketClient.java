package com.cxh.socket.tcp;



import sun.security.jca.Providers;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;

/**
 * 安全套接字连接的客户端
 */
public class SecuritySocketClient {

    public static  int PORT =3305;
    public static String ADDRESS = "127.0.0.1";

    public static void main(String [] args) throws Exception{
        InputStream in = getTrustStoreKey();
        SSLSocket socket = getClientSSlSocket(ADDRESS,PORT,in);
        socket.getOutputStream().write("hello,SSL Connection".getBytes("utf-8"));
        socket.close();
    }

    private static InputStream getTrustStoreKey() {
        return SecuritySocketClient.class.getResourceAsStream("/clientTrustStore.jks");
    }

    /**
     * 获取一个客户端使用的SSLSocket
     * @param descAddress 服务端的目标地址
     * @param port 服务端的端口
     * @param trustKeyStore 受信任的信任证书列表，里面包含服务器的证书。客户端根据这个信任证书判断连接的服务器是否可信
     * @return trustKeyStore
     */
    private static SSLSocket getClientSSlSocket(String descAddress,int port,InputStream trustKeyStore) throws Exception{
        //获取jks的keyStore实现
        KeyStore keyStore = KeyStore.getInstance("jks");
        //加载指定的keyStore对象，可传密码可不传。传了密码将对密钥库进行完整性校验
        keyStore.load(trustKeyStore, "123456".toCharArray());
        //使用默认算法构建密钥管理仓库
        TrustManagerFactory keyManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        //使用一个keystore来初始化一个密钥管理库
        keyManagerFactory.init(keyStore);
        //构建一个基于TLSv1.2协议的SSLContext
        SSLContext context = SSLContext.getInstance("TLSv1.2");

        //初始化SSLContext,使用信任证书列表来初始化它，这将赋予客户端一双火眼金睛，识别有效的服务端
        context.init(null, keyManagerFactory.getTrustManagers(), null);
        SSLSocketFactory socketFactory = context.getSocketFactory();
        SSLSocket  sslSocket = (SSLSocket)socketFactory.createSocket(ADDRESS,PORT);
        return sslSocket;
    }
}
