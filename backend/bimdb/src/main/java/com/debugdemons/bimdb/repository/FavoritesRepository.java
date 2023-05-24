package com.debugdemons.bimdb.repository;

import com.debugdemons.bimdb.model.FavoriteMedia;
import com.debugdemons.bimdb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FavoritesRepository extends JpaRepository<FavoriteMedia, Long> {

    boolean existsByUserAndTypeAndApiId(User user, String type, Long apiId);

    Integer deleteByUserAndTypeAndApiId(User user, String type, Long apiId);

    @Query("SELECT f.apiId FROM FavoriteMedia f WHERE f.user = :user AND f.type = :type")
    Set<Long> findAllApiIdsByUserAndType(@Param("user") User user, @Param("type") String type);

    List<FavoriteMedia> findAllByUserAndType(@Param("user") User user, @Param("type") String type);
}
