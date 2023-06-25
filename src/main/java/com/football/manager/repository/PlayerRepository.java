package com.football.manager.repository;

import com.football.manager.entity.Player;
import com.football.manager.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Page<Player> findByNameContaining(String name, Pageable pageable);

}
