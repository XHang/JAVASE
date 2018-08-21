package com.Nio.Example;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/*
 * 聚集写入和分散读取的示例程序
 */
public class ScatteringAndGatheringExample {

	  static private final int firstHeaderLength = 2;
	  static private final int secondHeaderLength = 4;
	  static private final int bodyLength = 6;

	   public static  void main( String args[] ) throws Exception {
	 

	    int port = Integer.parseInt( "455");

	    ServerSocketChannel ssc = ServerSocketChannel.open();
	    InetSocketAddress address = new InetSocketAddress( port );
	    ssc.socket().bind( address );

	    int messageLength =
	      firstHeaderLength + secondHeaderLength + bodyLength;

	    ByteBuffer buffers[] = new ByteBuffer[3];
	    buffers[0] = ByteBuffer.allocate( firstHeaderLength );
	    buffers[1] = ByteBuffer.allocate( secondHeaderLength );
	    buffers[2] = ByteBuffer.allocate( bodyLength );

	    SocketChannel sc = ssc.accept();

	    while (true) {

	      // Scatter-read into buffers
	      int bytesRead = 0;
	      while (bytesRead < messageLength) {
	        long r = sc.read( buffers );
	        bytesRead += r;

	        System.out.println( "r "+r );
	        for (int i=0; i<buffers.length; ++i) {
	          ByteBuffer bb = buffers[i];
	          System.out.println( "b "+i+" "+bb.position()+" "+bb.limit() );
	        }
	      }

	      // Process message here

	      // Flip buffers
	      for (int i=0; i<buffers.length; ++i) {
	        ByteBuffer bb = buffers[i];
	        bb.flip();
	      }

	      // Scatter-write back out
	      long bytesWritten = 0;
	      while (bytesWritten<messageLength) {
	        long r = sc.write( buffers );
	        bytesWritten += r;
	      }

	      // Clear buffers
	      for (int i=0; i<buffers.length; ++i) {
	        ByteBuffer bb = buffers[i];
	        bb.clear();
	      }

	      System.out.println( bytesRead+" "+bytesWritten+" "+messageLength );
	    }
	}

}
