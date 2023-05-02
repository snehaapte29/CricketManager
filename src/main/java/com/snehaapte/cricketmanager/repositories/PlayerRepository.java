package com.snehaapte.cricketmanager.repositories;

import com.snehaapte.cricketmanager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository <Player, Long> {


}
