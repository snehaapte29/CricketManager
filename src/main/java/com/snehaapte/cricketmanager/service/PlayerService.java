package com.snehaapte.cricketmanager.service;

import com.snehaapte.cricketmanager.controller.request.NewPlayerRequest;
import com.snehaapte.cricketmanager.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PlayerService {
    List<Player> getAllPlayers();
    Player getById(Long id);
    Player create(NewPlayerRequest newPlayerRequest);
    void delete(Long id);


}
