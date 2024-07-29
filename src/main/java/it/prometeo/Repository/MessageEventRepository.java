package it.prometeo.Repository;

import it.prometeo.Entity.MessageEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageEventRepository extends JpaRepository<MessageEvent, Integer> {

    @Query("SELECT me FROM MessageEvent me WHERE me.code = :code AND me.source = :source AND me.creationDate = (SELECT MAX(me2.creationDate) FROM MessageEvent me2 WHERE me2.code = :code AND me2.source = :source)")
    MessageEvent findLatestByCodeAndDateAndSource(@Param("code") String code, @Param("source") String source);


}
