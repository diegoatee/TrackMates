package com.rungroup.SpringMVCPt1.service;

import com.rungroup.SpringMVCPt1.dto.ClubDto;
import com.rungroup.SpringMVCPt1.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    List<ClubDto> searchClubs(String query);

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(Long clubId);

    void updateClub(ClubDto clubDto);

    void deleteClub(Long clubId);
}
