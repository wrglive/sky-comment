package com.marshall.sky.comment.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import com.marshall.sky.comment.model.Comment;
import com.marshall.sky.comment.model.TypeEnum;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class CommentRedisManageImpl implements CommentRedisManage {

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  private static final String BUILD_COMMENT_COUNT_KEY = "sky.comment.build.count";

  private ValueOperations<String, String> redisString() {
    return stringRedisTemplate.opsForValue();
  }

  private ListOperations<String, String> redisList() {
    return stringRedisTemplate.opsForList();
  }

  private String getAuthorListKey(long userId) {
    return "sky.author.comment.list:" + userId;
  }

  private String getCommentPojoKey(long id) {
    return "sky.comment.pojo.id:" + id;
  }

  @Override
  public boolean create(Comment comment) {
    redisString().set(getCommentPojoKey(comment.getId()),
        JSONObject.toJSONString(comment));

    redisList().leftPush(getAuthorListKey(comment.getUserId()), String.valueOf(comment.getId()));
    redisList().trim(getAuthorListKey(comment.getUserId()), 0, 3000);
    return true;
  }

  @Override
  public List<Comment> multiGet(Collection<Long> ids) {
    if (CollectionUtils.isEmpty(ids)) {
      return Lists.newArrayList();
    }

    List<String> keyList = ids.stream().filter(Objects::nonNull)
        .map(this::getCommentPojoKey)
        .collect(Collectors.toList());

    return redisString().multiGet(keyList).stream().filter(StringUtils::isNotBlank)
        .map(json -> JSONObject.parseObject(json, Comment.class))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Comment> getById(long id) {
    String key = getCommentPojoKey(id);
    String value = redisString().get(key);

    return Optional.ofNullable(JSONObject.parseObject(value, Comment.class));
  }

  @Override
  public List<Long> listAuthorComment(long userId, int page, int count) {
    String authorKey = getAuthorListKey(userId);
    return redisList().range(authorKey, page * count,
        (page + 1) * count - 1)
        .stream()
        .filter(StringUtils::isNumeric)
        .map(Longs::tryParse)
        .collect(Collectors.toList());
  }

  @Override
  public long buildCommentId(TypeEnum type) {
    long buildCount = redisString().increment(BUILD_COMMENT_COUNT_KEY, 1);
    stringRedisTemplate.expire(BUILD_COMMENT_COUNT_KEY, 1000, TimeUnit.MILLISECONDS);
    return System.currentTimeMillis() * 1000 + type.getIndex() * 100 + buildCount;
  }
}
