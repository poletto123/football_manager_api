package com.football.manager.dto;

import com.football.manager.entity.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayersUpdateDTO {

    private long teamId;

    private List<Player> players;

    public PlayersUpdateDTO(int teamId, List<Player> players) {
        this.teamId = teamId;
        this.players = players;
    }

    public PlayersUpdateDTO() {
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
