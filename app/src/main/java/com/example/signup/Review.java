package com.example.signup;

public class Review {

    private Integer reviewID;

    private Integer userID;

    private Integer gameID;
    private String gameImage;
    private String gameName;
    private String userComment;
    private String username;

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public Review(Integer gameID, Integer userID, String username, String userComment){
        this.userID = userID;
        this.gameID = gameID;
        this.userComment = userComment;
        this.username = username;
    }

    public Review(Integer reviewID, Integer userID, Integer gameID, String gameImage, String gameName, String userComment, String username) {
        this.reviewID = reviewID;
        this.userID = userID;
        this.gameID = gameID;
        this.gameImage = gameImage;
        this.gameName = gameName;
        this.userComment = userComment;
        this.username = username;
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

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
