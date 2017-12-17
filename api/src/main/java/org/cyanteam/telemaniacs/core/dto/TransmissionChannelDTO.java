package org.cyanteam.telemaniacs.core.dto;

/**
 * @author Simona Tinkova
 * @author Michael Le
 */
public class TransmissionChannelDTO {
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
		if (!(o instanceof TransmissionChannelDTO)) return false;

		TransmissionChannelDTO that = (TransmissionChannelDTO) o;

		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		return result;
	}
}
