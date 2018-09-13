package com.xxx.demo.Repository;

import com.xxx.demo.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query(value = "select * from message where user_id = ?1 and bot_id = ?2 order by send_time",nativeQuery = true)
    public List<Message> getMessageRecord (int user_id, int bot_id);


}
