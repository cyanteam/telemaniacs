package org.cyanteam.telemaniacs.core.dto;

/**
 * @author Simona Tinkova
 * @author Michael Le
 */
public class TransmissionOccurrenceCreateDTO {
	private Long id;

	private Long channelId;

	private Long transmissionId;

	private String partName;

	private String startDate;

	private boolean isRerun;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getTransmissionId() {
		return transmissionId;
	}

	public void setTransmissionId(Long transmissionId) {
		this.transmissionId = transmissionId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public boolean isRerun() {
		return isRerun;
	}

	public void setRerun(boolean rerun) {
		isRerun = rerun;
	}
}
