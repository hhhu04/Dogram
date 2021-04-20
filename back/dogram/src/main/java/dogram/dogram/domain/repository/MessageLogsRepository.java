package dogram.dogram.domain.repository;

import dogram.dogram.domain.entity.MessageLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageLogsRepository extends JpaRepository<MessageLogs,Long> {

}
