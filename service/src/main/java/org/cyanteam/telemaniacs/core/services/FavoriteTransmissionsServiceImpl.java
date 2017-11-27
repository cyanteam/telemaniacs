package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Simona Tinkova
 */
@Service
public class FavoriteTransmissionsServiceImpl implements FavoriteTransmissionsService {
	@Override
	public void followTransmission(Transmission transmission, User user) {
		List<Transmission> favoriteTransmissions = user.getFavoriteTransmissions();

		if (!favoriteTransmissions.contains(transmission)) {
			favoriteTransmissions.add(transmission);
		}

		user.setFavoriteTransmissions(favoriteTransmissions);
	}

	@Override
	public void unfollowTransmission(Transmission transmission, User user) {
		List<Transmission> favoriteTransmissions = user.getFavoriteTransmissions();

		if (favoriteTransmissions.contains(transmission)) {
			favoriteTransmissions.remove(transmission);
		}

		user.setFavoriteTransmissions(favoriteTransmissions);
	}

	@Override
	public List<Transmission> getFavoriteTransmissionsByUser(User user) {
		return user.getFavoriteTransmissions();
	}

	@Override
	public List<Transmission> getUpcomingFavoriteTransmissionsByUser(User user, Duration maxTimeSpan) {
		if (user.getFavoriteTransmissions() == null) {
			return new ArrayList<>();
		}

		return user.getFavoriteTransmissions().stream()
				.filter(p -> p.getOccurrences().stream()
						.anyMatch(o ->
								Duration.between(LocalDateTime.now(), o.getStartDate()).getSeconds() <= maxTimeSpan.getSeconds()))
				.collect(Collectors.toList());

	}
}
