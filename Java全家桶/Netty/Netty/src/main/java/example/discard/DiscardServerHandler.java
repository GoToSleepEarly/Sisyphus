package example.discard;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    ThreadLocal<String> tl = new ThreadLocal<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            //System.out.println(in.toString(CharsetUtil.UTF_8));
            //ctx.writeAndFlush(msg);
            tl.set(in.toString(CharsetUtil.UTF_8));
            Thread.sleep(10000);
            System.out.println(tl.get());
        } finally {
            // write会自动给计数-1，所以只有读取时需要控制计数
            // ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("thanks".getBytes()));
        tl.remove();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
