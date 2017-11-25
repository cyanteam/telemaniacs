package org.cyanteam.telemaniacs.core.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Simona Tinkova
 */
public class VotingDTO {
	private Long id;

	private String ipAddress;

	private int rank;

	private String comment;

	private UserDTO user;

	private TransmissionDTO transmission;


	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public TransmissionDTO getTransmission() {
		return transmission;
	}

	public void setTransmission(TransmissionDTO transmission) {
		this.transmission = transmission;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof VotingDTO)) return false;

		VotingDTO votingDTO = (VotingDTO) o;

		return getIpAddress() != null ? getIpAddress().equals(votingDTO.getIpAddress()) : votingDTO.getIpAddress() == null;
	}

	@Override
	public int hashCode() {
		return getIpAddress() != null ? getIpAddress().hashCode() : 0;
	}
}
