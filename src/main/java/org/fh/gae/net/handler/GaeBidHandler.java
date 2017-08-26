package org.fh.gae.net.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.fh.gae.net.ThreadPool;
import org.fh.gae.net.vo.BidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * bid请求处理器
 */
@Component
@ChannelHandler.Sharable
@Slf4j
public class GaeBidHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private ThreadPool pool;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BidRequest request = (BidRequest) msg;

        // 将广告请求提交到业务线程池中处理
        pool.execute(request, ctx);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    }
}
