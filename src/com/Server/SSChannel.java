package com.Server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.SQLOutput;
import java.util.Iterator;

public class SSChannel{



	public void SSCh() throws Exception{
		Selector selector = Selector.open();

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress(9999));
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		ByteBuffer byteBuffer = ByteBuffer.allocate(4);

		while (true){
			int select = selector.select();
			if (select==0)
				continue;

			Iterator<SelectionKey> selectedKey = selector.selectedKeys().iterator();

			while (selectedKey.hasNext()){
				SelectionKey selectionKey = selectedKey.next();

				try {
					if (selectionKey.channel() == serverSocketChannel) {
						SocketChannel socketChannel = serverSocketChannel.accept();
						socketChannel.configureBlocking(false);

						socketChannel.register(selector, SelectionKey.OP_READ);
					} else {

						((SocketChannel) selectionKey.channel()).read(byteBuffer);

						byteBuffer.flip();

						System.out.println("buffer=" + new String(byteBuffer.array(),
								byteBuffer.position(), byteBuffer.remaining()));
						byteBuffer.clear();

					}
				}finally {
					selectedKey.remove();
				}
					
			}
		}
	}
}
