package com.snehaapte.cricketmanager.service;

import com.snehaapte.cricketmanager.controller.request.NewPlayerRequest;
import com.snehaapte.cricketmanager.model.Player;
import com.snehaapte.cricketmanager.model.PlayerStyle;
import com.snehaapte.cricketmanager.repositories.PlayerRepository;
import com.snehaapte.cricketmanager.repositories.PlayerStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository repository;
    private PlayerStyleRepository styleRepository;

    @Autowired
    public void setRepository(PlayerRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setStyleRepository(PlayerStyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    @Override
    public Player getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Player create(NewPlayerRequest newPlayerRequest) {
        //insert Validations here
        //Date validation
        try {
            LocalDate.parse(newPlayerRequest.getBirthdate(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch(DateTimeParseException e){
            throw new IllegalArgumentException("Invalid date");
        }
        //Check if a valid PlayerStyle object (find it based on the style name) exists in database and return the same
        //PlayerStyle playerStyleObj = styleRepository.getPlayerByStyle(newPlayerRequest.getStyle());
        /*if(playerStyleObj == null) {
            throw new IllegalArgumentException("Invalid PlayerStyle");
        }*/
        PlayerStyle playerStyleObj = styleRepository.getPlayerByStyle(newPlayerRequest.getStyle())
                .orElseThrow( () -> new IllegalArgumentException("Invalid player style"));

        Player playerObj = new Player();
        playerObj.setBirthdate(newPlayerRequest.getBirthdate());
        playerObj.setName(newPlayerRequest.getName());
        playerObj.setLeftHandBatsman(newPlayerRequest.getLeftHandBatsman());
        playerObj.setLeftHandBowler(newPlayerRequest.getLeftHandBowler());
        playerObj.setStyle(playerStyleObj);
        return repository.saveAndFlush(playerObj);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
/*
    private Map<Long, Player> map = new HashMap<Long, Player>();
    private Long id = 1L;

    @Override
    public List<Player> getAllPlayers() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Player getById(Long id) {
        return map.get(id);
    }

    @Override
    public Player create(Player player) {
        //insert Validations here
        map.put(id++,player);
        return player;
    }

    @Override
    public void delete(Long id) {
        map.remove(id);
    }

 */

