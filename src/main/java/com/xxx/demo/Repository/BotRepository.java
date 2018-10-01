package com.xxx.demo.Repository;

import com.xxx.demo.Entity.Bot;
import com.xxx.demo.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BotRepository extends JpaRepository<Bot,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into bot set introduction = ?1 ",nativeQuery = true)
    public String addBot(String introduction);

    @Query(value = "select * from bot where bot_id = ?1 ",nativeQuery = true)
    public String getBot(int botID);

    @Query (value = "select introduction from bot where name = ?1 ",nativeQuery = true)
    public String getIntroduction(String name);

    @Query (value = "select * from bot ",nativeQuery = true)
    public List<Bot> getAllBot();
}
