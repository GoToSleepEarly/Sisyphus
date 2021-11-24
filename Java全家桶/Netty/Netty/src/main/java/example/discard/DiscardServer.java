package example.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    //telnet 127.0.0.1 8080
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new ChannelInitializer<ServerSocketChannel>() {
                        @Override
                        public void initChannel(ServerSocketChannel ch) throws Exception {
                            ByteBuf a = Unpooled.copiedBuffer("a".getBytes());
                            ByteBuf b1 = Unpooled.copiedBuffer("b".getBytes());
                            ByteBuf c = Unpooled.copiedBuffer("c".getBytes());
                            ch.pipeline().addLast(new DiscardServerHandler())
                                    .addLast(new DelimiterBasedFrameDecoder(100, false, false, a, b1, c));
                        }
                    })
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf a = Unpooled.copiedBuffer("a".getBytes());
                            ByteBuf b1 = Unpooled.copiedBuffer("b".getBytes());
                            ByteBuf c = Unpooled.copiedBuffer("c".getBytes());
                            ch.pipeline().addLast(new DiscardServerHandler())
                                    .addLast(new DelimiterBasedFrameDecoder(100, false, false, a, b1, c));
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口，开始接收
            ChannelFuture f = b.bind(port).sync();


            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }

        new DiscardServer(port).run();
    }

}
