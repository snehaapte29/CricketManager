package com.snehaapte.cricketmanager.service;

import com.snehaapte.cricketmanager.model.PlayerStyle;
import com.snehaapte.cricketmanager.repositories.PlayerStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerStyleServiceImpl implements PlayerStyleService{

    private PlayerStyleRepository styleRepository;
    private final List<String> styleList = List.of("batsman", "bowler", "allrounder");

    @Autowired
    public void setStyleRepository(PlayerStyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    @PostConstruct
    public List<PlayerStyle> initializePlayerStyles() {

        //Get player styles from database
        //Find a set of style names (String names) from the db player styles.
        Set<String> styleNamesfromdb = styleRepository.findAll().stream().map(PlayerStyle::getStyle).collect(Collectors.toSet());

        //iterate through stylelist and check if all styles in stylelist are present in the database list
        /**for(String styleName : styleList){
            // if any name is absent, add it to a new list
            if (!styleNamesfromdb.contains(styleName)){
                PlayerStyle newPlayerStyle = new PlayerStyle();
                newPlayerStyle.setStyle(styleName);
                newStyleList.add(newPlayerStyle);
            }
        }**/

        List<PlayerStyle> newStyleList = styleList.stream()
                .filter(e->!styleNamesfromdb.contains(e))
                .map(e->{
                    PlayerStyle newPlayerStyle = new PlayerStyle();
                    newPlayerStyle.setStyle(e);
                    return newPlayerStyle;
                }).collect(Collectors.toList());

        //send the new list to database
        return styleRepository.saveAll(newStyleList);
    }
}
