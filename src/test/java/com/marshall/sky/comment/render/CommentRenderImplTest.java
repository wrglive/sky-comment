package com.marshall.sky.comment.render;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.marshall.sky.comment.client.UserClient;
import com.marshall.sky.comment.helper.CommentHelper;
import com.marshall.sky.comment.model.UserInfo;
import com.marshall.sky.core.test.BaseServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("comment test")
public class CommentRenderImplTest extends BaseServiceTest {
  @InjectMocks
  CommentRenderImpl commentRender;
  @Mock
  UserClient userClient;

  @Test
  public void batchRender() {
    when(userClient.get(anyLong()))
        .thenReturn(UserInfo
            .builder()
            .userId(111L)
            .build());

    commentRender.batchRender(CommentHelper.mockComments(10));

  }
}