package org.cyanteam.telemaniacs.core.helpers;

import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.entities.Voting;

/**
 * Helper class for building Voting (for test purposes)
 * @author Miroslav Kubus
 */
public class VotingBuilder {
    private Long id;
    private String ipAddress;
    private int rank;
    private String comment;
    private User user;
    private Transmission transmission;

    public VotingBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public VotingBuilder ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public VotingBuilder rank(int rank) {
        this.rank = rank;
        return this;
    }

    public VotingBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }

    public VotingBuilder user(User user) {
        this.user = user;
        return this;
    }
    
    public VotingBuilder transmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }
    
    public Voting build() {
        Voting voting = new Voting();
        voting.setId(id);
        voting.setComment(comment);
        voting.setIpAddress(ipAddress);
        voting.setRank(rank);
        voting.setTransmission(transmission);
        voting.setUser(user);
        
        return voting;
    }

    public static VotingBuilder sampleLowVotingBuilder() {
        return new VotingBuilder()
                .comment("Poor quality")
                .ipAddress("192.168.0.1")
                .rank(0);
    }

    public static VotingBuilder sampleMediumVotingBuilder() {
        return new VotingBuilder()
                .comment("Medium quality")
                .ipAddress("192.168.0.1")
                .rank(3);
    }

    public static VotingBuilder sampleHighVotingBuilder() {
        return new VotingBuilder()
                .comment("Exclusive quality")
                .ipAddress("192.168.0.1")
                .rank(5);
    }
}
