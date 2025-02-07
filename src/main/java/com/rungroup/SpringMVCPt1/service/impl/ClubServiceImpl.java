package com.rungroup.SpringMVCPt1.service.impl;

import com.rungroup.SpringMVCPt1.dto.ClubDto;
import com.rungroup.SpringMVCPt1.mapper.ClubMapper;
import com.rungroup.SpringMVCPt1.models.Club;
import com.rungroup.SpringMVCPt1.repository.ClubRepository;
import com.rungroup.SpringMVCPt1.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;

    @Autowired //This will automatically inject the bean of type ClubRepository
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();

        //Map all entities to DTOs
        return clubs.stream().map((club) -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);

        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        Club club = ClubMapper.mapToClub(clubDto);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(Long clubId) {
        //Get the club from the clubId
        Club club = clubRepository.findById(clubId).get();

        //We need to map the Club to be updated to a ClubDto
        return ClubMapper.mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = ClubMapper.mapToClub(clubDto);

        clubRepository.save(club);
    }

    @Override
    public void deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
    }
}
