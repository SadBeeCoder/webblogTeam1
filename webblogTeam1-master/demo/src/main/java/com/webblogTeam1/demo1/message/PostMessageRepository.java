package com.webblogTeam1.demo1.message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMessageRepository extends CrudRepository<PostMessage, Long> {
    List<PostMessage> findAllByOrderByPostedAtDesc();
}
