package com.janeroad.responsibility;

public class HandlerB extends PostHandler{
    @Override
    public void handlerRequest(Post post) {
        String content = post.getContent();
        content = content.replace("游戏推广", "**");
        post.setContent(content);
        System.out.println("过滤游戏推广完成");
        next(post);
    }
}
