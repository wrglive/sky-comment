package com.marshall.sky.comment.render;

import com.marshall.sky.comment.model.ApiComment;
import com.marshall.sky.comment.model.Comment;
import java.util.Collection;
import java.util.List;

public interface CommentRender {
  List<ApiComment> batchRender(Collection<Comment> comments);
}
