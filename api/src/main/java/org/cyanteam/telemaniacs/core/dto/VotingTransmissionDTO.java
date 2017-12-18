package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.enums.TransmissionType;

import java.util.List;

/**
 * @author Simona Tinkova
 * @author Michael Le
 */


public class VotingTransmissionDTO {

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof VotingTransmissionDTO)) return false;

		VotingTransmissionDTO that = (VotingTransmissionDTO) o;

		return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}

}
