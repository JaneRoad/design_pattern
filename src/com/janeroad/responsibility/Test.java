package com.janeroad.responsibility;

public class Test {
    public static void main(String[] args) {
        PostHandler handlerA = new HandlerA();
        PostHandler handlerB = new HandlerB();
        handlerA.setHandler(handlerB);
        Post post = new Post();
        post.setContent("正常内容，广告，游戏推广");
        System.out.println("过滤前的内容:" + post.getContent());
        handlerA.handlerRequest(post);
        System.out.println("过滤后的内容:" + post.getContent());
    }
}
