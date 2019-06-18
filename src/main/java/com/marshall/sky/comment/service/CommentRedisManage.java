package com.marshall.sky.comment.service;

import com.marshall.sky.comment.model.Comment;

public interface CommentRedisManage {

  boolean create(Comment comment);
}
