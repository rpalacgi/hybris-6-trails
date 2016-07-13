package de.hybris.platform.cuppytrail.events;

import de.hybris.platform.servicelayer.event.events.AbstractEvent;

public class CapacityEvent extends AbstractEvent {

	private final Integer capacity;

	private final String code;

	public CapacityEvent(Integer capacity, String code) {
		this.capacity = capacity;
		this.code = code;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "CapacityEvent{" +
				"capacity=" + capacity +
				", code='" + code + '\'' +
				'}';
	}
}
