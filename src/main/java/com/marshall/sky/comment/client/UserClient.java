package com.marshall.sky.comment.client;

import com.marshall.sky.comment.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sky-cloud-user", fallback = UserClientHystrix.class)
public interface UserClient {

  @GetMapping(value = "/user/get.json")
  public UserInfo get(@RequestParam("id") long id);

}
