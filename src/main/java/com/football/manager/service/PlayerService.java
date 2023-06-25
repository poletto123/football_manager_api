package com.football.manager.service;

import com.football.manager.dto.PlayersUpdateDTO;
import com.football.manager.entity.Player;
import com.football.manager.entity.Team;
import com.football.manager.repository.PlayerRepository;
import com.football.manager.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.football.manager.service.UtilityHelper.createSortObject;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    private Logger logger = LoggerFactory.getLogger(PlayerService.class);

    public Page<Player> getPlayersByName(int page, int size, String[] sort, String name) {
        Sort sortCriteria = createSortObject(sort);
        PageRequest pageable = PageRequest.of(page, size, sortCriteria);
        return playerRepository.findByNameContaining(name, pageable);
    }

    public Team updatePlayersByTeamId(PlayersUpdateDTO dto) {
        Optional<Team> teamOptional = teamRepository.findById(dto.getTeamId());

        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();

            List<Player> updatedPlayers = new ArrayList<>();

            for (Player player : dto.getPlayers()) {
                Page<Player> players = playerRepository.findByNameContaining(player.getName(), Pageable.unpaged());

                Optional<Player> playerOptional = players.stream()
                        .filter(playerTemp -> playerTemp.getName().equalsIgnoreCase(player.getName()))
                        .findFirst();
                if (playerOptional.isPresent()) {
                    Player existingPlayer = playerOptional.get();
                    existingPlayer.setName(player.getName());
                    existingPlayer.setPosition(player.getPosition());
                    existingPlayer.setTeam(team);
                    updatedPlayers.add(existingPlayer);
                } else {
                    Player newPlayer = new Player();
                    newPlayer.setName(player.getName());
                    newPlayer.setPosition(player.getPosition());
                    newPlayer.setTeam(team);
                    updatedPlayers.add(newPlayer);
                }
            }
            team.setPlayers(updatedPlayers);

            return teamRepository.save(team);
        } else {
            logger.error("Team with id " + dto.getTeamId() + " not found.");
            throw new IllegalArgumentException("Team with id " + dto.getTeamId() + " not found.");
        }
    }

}
