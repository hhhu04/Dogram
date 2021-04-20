package dogram.dogram.domain.repository;

import dogram.dogram.domain.entity.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeListRepository extends JpaRepository<LikeList,Long> {

}
