package com.football.manager;

import com.football.manager.entity.Team;
import com.football.manager.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class TeamsTest {

    @Autowired
    private TeamService teamservice;

    @Test
    void getListTeams() {
        Page<Team> pages = teamservice.getAllTeams(0, 5, new String[0]);
        assertTrue(pages.getContent().size() > 1 && pages.getContent().size() <= 5);
    }

    @Test
    void createNewTeam() {
        Team team = teamservice.createNewTeam(new Team("name", "acronym", new BigDecimal("1")));
        assertEquals("name", team.getName());
    }

    @Test
    void getListTeamsByName() {
        Page<Team> pages = teamservice.getTeamsByName(0, 5, new String[0], "Nice");
        assertEquals("Olympique Gymnaste Club de Nice", pages.getContent().get(0).getName());
    }

    @Test
    void getListTeamsSortingByNameAscending() {
        List<Team> teams = teamservice.getAllTeams(0, 5, new String[]{"name"}).getContent();
        char first = teams.get(0).getName().charAt(0);
        char last = teams.get(teams.size() - 1).getName().charAt(0);
        assertTrue(last >= first);
    }

    @Test
    void getListTeamsSortingByNameDescending() {
        List<Team> teams = teamservice.getAllTeams(0, 5, new String[]{"-name"}).getContent();
        char first = teams.get(0).getName().charAt(0);
        char last = teams.get(teams.size() - 1).getName().charAt(0);
        assertTrue(first >= last);
    }

    @Test
    void getListTeamsSortingByAcronymAscending() {
        List<Team> teams = teamservice.getAllTeams(0, 5, new String[]{"name"}).getContent();
        char first = teams.get(0).getAcronym().charAt(0);
        char last = teams.get(teams.size() - 1).getAcronym().charAt(0);
        assertTrue(last >= first);
    }

    @Test
    void getListTeamsSortingByAcronymDescending() {
        List<Team> teams = teamservice.getAllTeams(0, 5, new String[]{"-name"}).getContent();
        char first = teams.get(0).getAcronym().charAt(0);
        char last = teams.get(teams.size() - 1).getAcronym().charAt(0);
        assertTrue(first >= last);
    }

    @Test
    void getListTeamsSortingByBudgetAscending() {
        List<Team> teams = teamservice.getAllTeams(0, 5, new String[]{"budget"}).getContent();
        BigDecimal first = teams.get(0).getBudget();
        BigDecimal last = teams.get(teams.size() - 1).getBudget();
        assertTrue(first.compareTo(last) < 0);
    }

    @Test
    void getListTeamsSortingByBudgetDescending() {
        List<Team> teams = teamservice.getAllTeams(0, 5, new String[]{"-budget"}).getContent();
        BigDecimal first = teams.get(0).getBudget();
        BigDecimal last = teams.get(teams.size() - 1).getBudget();
        assertTrue(last.compareTo(first) < 0);
    }

}