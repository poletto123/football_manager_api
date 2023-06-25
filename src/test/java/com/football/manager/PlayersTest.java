package com.football.manager;

import com.football.manager.dto.PlayersUpdateDTO;
import com.football.manager.entity.Player;
import com.football.manager.entity.Position;
import com.football.manager.entity.Team;
import com.football.manager.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PlayersTest {

    @Autowired
    private PlayerService playerService;

    @Test
    void getListPlayersByName() {
        Page<Player> pages = playerService.getPlayersByName(0, 5, new String[0], "Neymar");
        assertEquals("Neymar Jr", pages.getContent().get(0).getName());
    }

    @Test
    void updatePlayerByTeamId() {
        PlayersUpdateDTO dto = new PlayersUpdateDTO(1, List.of(
                new Player("name", Position.F),
                new Player("Neymar Jr", Position.F))
        );
        Team team = playerService.updatePlayersByTeamId(dto);
        assertTrue(team.getPlayers().get(0).getName().equals("name") && team.getPlayers().get(1).getName().equals("Neymar Jr"));
    }

    @Test
    void updatePlayerByInvalidTeamId() {
        PlayersUpdateDTO dto = new PlayersUpdateDTO(99999, List.of(
                new Player("name", Position.F),
                new Player("Neymar Jr", Position.F))
        );
        assertThrows(IllegalArgumentException.class, () -> playerService.updatePlayersByTeamId(dto), "This method is not throwing an exception as it should");
    }

}