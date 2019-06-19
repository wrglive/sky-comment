package com.marshall.sky.comment.client;

import com.marshall.sky.comment.model.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 熔断类
 */
@Component
public class UserClientHystrix implements UserClient {

  @Override
  public UserInfo get(@RequestParam("id") long id) {
    return null;
  }
}
