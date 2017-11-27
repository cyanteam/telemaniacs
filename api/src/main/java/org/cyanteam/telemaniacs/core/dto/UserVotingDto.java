package org.cyanteam.telemaniacs.core.dto;

/**
 * User voting DTO
 */
public class UserVotingDto {
    private int rank;

    private String comment;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
