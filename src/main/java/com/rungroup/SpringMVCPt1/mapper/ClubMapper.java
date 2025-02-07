package com.rungroup.SpringMVCPt1.mapper;

import com.rungroup.SpringMVCPt1.dto.ClubDto;
import com.rungroup.SpringMVCPt1.models.Club;

import java.util.stream.Collectors;

public class ClubMapper {
    public static Club mapToClub(ClubDto clubDto) {
        Club club = Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .content(clubDto.getContent())
                .photoURL(clubDto.getPhotoURL())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();

        return club;
    }

    public static ClubDto mapToClubDto(Club club) {
        //This is possible with the @Builder annotation
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .photoURL(club.getPhotoURL())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .events(club.getEvents().stream().map((event) -> EventMapper.mapToEventDto(event)).collect(Collectors.toList()))
                .build();

        return clubDto;
    }
}
