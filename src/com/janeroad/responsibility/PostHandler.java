package com.janeroad.responsibility;

import java.util.Objects;

public abstract class PostHandler {
    private PostHandler handler;

    public void setHandler(PostHandler handler){
        this.handler = handler;
    }

    public abstract void handlerRequest(Post post);

    protected final void next(Post post){
        if (Objects.nonNull(this.handler)){
            this.handler.handlerRequest(post);
        }
    }
}
