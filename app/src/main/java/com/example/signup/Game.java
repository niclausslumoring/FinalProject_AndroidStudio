package com.example.signup;

public class Game {
    private Integer gameID;
    private String gameImage;
    private String gameName;

    private Integer gameRating;
    private String gameDesc;

    public Integer getGameRating() {
        return gameRating;
    }

    public void setGameRating(Integer gameRating) {
        this.gameRating = gameRating;
    }

    public String getGameDesc() {
        return gameDesc;
    }

    public void setGameDesc(String gameDesc) {
        this.gameDesc = gameDesc;
    }

    public Game(Integer gameID, String gameImage, String gameName, String gameGenre, Integer gameRating, String gameDesc, String gamePrice) {
        this.gameID = gameID;
        this.gameImage = gameImage;
        this.gameName = gameName;
        this.gameRating = gameRating;
        this.gameDesc = gameDesc;
        this.gameGenre = gameGenre;
        this.gamePrice = gamePrice;
    }

    private String gameGenre;
    private String gamePrice;



    public Integer getGameID() {
        return gameID;
    }
    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }
    public String getGameImage() {
        return gameImage;
    }

    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public String getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(String gamePrice) {
        this.gamePrice = gamePrice;
    }
}
