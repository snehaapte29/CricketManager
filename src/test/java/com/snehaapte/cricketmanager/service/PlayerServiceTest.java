package com.snehaapte.cricketmanager.service;

import com.snehaapte.cricketmanager.controller.request.NewPlayerRequest;
import com.snehaapte.cricketmanager.model.Player;
import com.snehaapte.cricketmanager.model.PlayerStyle;
import com.snehaapte.cricketmanager.repositories.PlayerRepository;
import com.snehaapte.cricketmanager.repositories.PlayerStyleRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlayerServiceTest {

    @MockBean
    PlayerRepository repository;

    @MockBean
    PlayerStyleRepository styleRepository;

    @Autowired
    PlayerService service;

    @Test
    public void test_create_withBadDate(){
        NewPlayerRequest newPlayerRequest = new NewPlayerRequest();
        newPlayerRequest.setBirthdate("1976-29-29");
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.create(newPlayerRequest));
    }

    @Test
    public void test_create_withBadStyle(){
        NewPlayerRequest newPlayerRequest = new NewPlayerRequest();
        newPlayerRequest.setBirthdate("1976-11-29");
        newPlayerRequest.setStyle("allrounders");
        when(styleRepository.getPlayerByStyle("allrounders")).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.create(newPlayerRequest));
    }

    @Test
    public void test_create(){
        NewPlayerRequest newPlayerRequest = new NewPlayerRequest();
        newPlayerRequest.setName("testname");
        newPlayerRequest.setBirthdate("1976-11-29");
        newPlayerRequest.setStyle("allrounder");
        newPlayerRequest.setLeftHandBatsman(true);
        newPlayerRequest.setLeftHandBowler(false);

        PlayerStyle playerStyleObject = new PlayerStyle();
        playerStyleObject.setStyle("allrounder");

        when(styleRepository.getPlayerByStyle("allrounder")).thenReturn(Optional.of(playerStyleObject));

        Player playerObj = new Player();
        playerObj.setBirthdate("1976-11-29");
        playerObj.setName("testname");
        playerObj.setLeftHandBatsman(true);
        playerObj.setLeftHandBowler(false);
        playerObj.setStyle(playerStyleObject);

        when(repository.saveAndFlush(Mockito.any(Player.class))).thenReturn(playerObj);
        Assertions.assertEquals(playerObj, service.create(newPlayerRequest));

    }

}
