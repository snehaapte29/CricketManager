package com.snehaapte.cricketmanager.repositories;

import com.snehaapte.cricketmanager.model.PlayerStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerStyleRepository extends JpaRepository<PlayerStyle, Long> {
    Optional<PlayerStyle> getPlayerByStyle(String style);
}
