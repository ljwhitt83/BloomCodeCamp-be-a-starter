package com.hcc.entities;

public class Assignment {
    private Long id;
    private String status;
    private int Number;
    private String githubUrl;
    private String branch;
    private String reviewVidueoUrl;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getReviewVidueoUrl() {
        return reviewVidueoUrl;
    }

    public void setReviewVidueoUrl(String reviewVidueoUrl) {
        this.reviewVidueoUrl = reviewVidueoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Assignment() {
    }

    public Assignment(String status, int number, String githubUrl, String branch, String reviewVidueoUrl, User user) {
        this.status = status;
        Number = number;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVidueoUrl = reviewVidueoUrl;
        this.user = user;
    }
}
