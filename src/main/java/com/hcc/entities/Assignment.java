package com.hcc.entities;


import javax.persistence.*;

@Entity
@Table(name="assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="status")
    private String status;

    @Column(name="number")
    private int Number;

    @Column(name="github_url")
    private String githubUrl;

    @Column(name="branch")
    private String branch;

    @Column(name="review_video_url")
    private String reviewVidueoUrl;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne()
    private User codeReview;

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
    public User getCodeReview() {
        return codeReview;
    }

    public void setCodeReview(User codeReview) {
        this.codeReview = codeReview;
    }

    public Assignment() {
    }



    public Assignment(String status, int number, String githubUrl, String branch, String reviewVidueoUrl, User user, User codeReview) {
        this.status = status;
        Number = number;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVidueoUrl = reviewVidueoUrl;
        this.user = user;
        this.codeReview = codeReview;
    }
}
