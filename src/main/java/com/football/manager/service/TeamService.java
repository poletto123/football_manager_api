package com.football.manager.service;

import com.football.manager.entity.Team;
import com.football.manager.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.football.manager.service.UtilityHelper.createSortObject;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    private Logger logger = LoggerFactory.getLogger(PlayerService.class);

    public Page<Team> getAllTeams(int page, int size, String[] sort) {
        Sort sortCriteria = createSortObject(sort);
        PageRequest pageable = PageRequest.of(page, size, sortCriteria);
        return teamRepository.findAll(pageable);
    }

    public Page<Team> getTeamsByName(int page, int size, String[] sort, String name) {
        Sort sortCriteria = createSortObject(sort);
        PageRequest pageable = PageRequest.of(page, size, sortCriteria);
        return teamRepository.findByNameContaining(name, pageable);
    }

    public Team createNewTeam(Team team) {
        return teamRepository.save(team);
    }

}
