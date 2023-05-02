package com.snehaapte.cricketmanager.controller;

import com.snehaapte.cricketmanager.controller.request.NewPlayerRequest;
import com.snehaapte.cricketmanager.model.Player;
import com.snehaapte.cricketmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/players")
public class PlayerController {

    // This will create an instance of PlayerService and put in this class
    // Spring will auto-wire this when PlayerController is built.

    private PlayerService service;

    @Autowired
    public void setService(PlayerService service) {
        this.service = service;
    }

    // Returns all the players for GET verb
    @GetMapping
    public List<Player> list(){
        return service.getAllPlayers();
        }

    // Get specific player by id
    @GetMapping
    @RequestMapping("{id}")
    public Player get(@PathVariable Long id){
        return service.getById(id);
    }

    // This shows that http verb POST is required
    // save and flush method saves objects and also commits to the database
    @PostMapping
    public Player create(@RequestBody @Valid final NewPlayerRequest newPlayerRequest){

        return service.create(newPlayerRequest);

    }

    @RequestMapping(value = "{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also need to check for children records before deleting
        service.delete(id);
    }

}
