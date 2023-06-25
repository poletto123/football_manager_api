package com.football.manager.controller;

import com.football.manager.dto.PlayersUpdateDTO;
import com.football.manager.entity.Player;
import com.football.manager.entity.Team;
import com.football.manager.service.PlayerService;
import com.football.manager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("footballmanager")
public class Controller {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/team")
    public ResponseEntity<Page<Team>> getAllTeams(
            @RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) String[] sort
            ) {
        return new ResponseEntity<>(
                teamService.getAllTeams(page, size, sort != null ? sort : new String[0]),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/team")
    public ResponseEntity<Team> createNewTeam(@RequestBody Team team) {
        return new ResponseEntity<>(
                teamService.createNewTeam(team),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "/team/{name}")
    public ResponseEntity<Page<Team>> getTeamsByName(
            @RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) String[] sort, @PathVariable String name
    ) {
        return new ResponseEntity<>(
                teamService.getTeamsByName(page, size, sort != null ? sort : new String[0], name),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/player/{name}")
    public ResponseEntity<Page<Player>> getPlayersByName(
            @RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) String[] sort, @PathVariable String name
    ) {
        return new ResponseEntity<>(
                playerService.getPlayersByName(page, size, sort != null ? sort : new String[0], name),
                HttpStatus.OK
        );
    }

    @PatchMapping(value = "/team/players")
    public ResponseEntity<Team> updatePlayersByTeamId(@RequestBody PlayersUpdateDTO dto) {
        return new ResponseEntity<>(
                playerService.updatePlayersByTeamId(dto),
                HttpStatus.OK
        );
    }




}
