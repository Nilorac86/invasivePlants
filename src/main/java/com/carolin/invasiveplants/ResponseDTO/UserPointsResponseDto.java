package com.carolin.invasiveplants.ResponseDTO;

public class UserPointsResponseDto {

    private int Points;

    public UserPointsResponseDto() {
    }

    public UserPointsResponseDto(int points) {
        Points = points;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }
}
