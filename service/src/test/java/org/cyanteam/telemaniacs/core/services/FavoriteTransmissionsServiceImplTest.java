package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.entities.*;
import org.cyanteam.telemaniacs.core.helpers.TransmissionBuilder;
import org.cyanteam.telemaniacs.core.helpers.TransmissionOccurrenceBuilder;
import org.cyanteam.telemaniacs.core.helpers.UserBuilder;
import org.cyanteam.telemaniacs.core.helpers.VotingBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cyanteam.telemaniacs.core.utils.ListUtils.createList;

/**
 * @author Simona Tinkova
 */

@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FavoriteTransmissionsServiceImplTest  {
	@Inject
	private FavoriteTransmissionsService favoriteTransmissionsService;

	private LocalDateTime mockDateTime = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
	private Channel channel = new Channel();

	private Transmission transmission1;
	private Transmission transmission2;
	private Transmission transmission3;

	private TransmissionOccurrence occurrence1;
	private TransmissionOccurrence occurrence2;
	private TransmissionOccurrence occurrence3;

	private Voting voting1;
	private Voting voting2;
	private Voting voting3;

	private User user1;
	private User user2;
	private User user3;

	@Before
	public void setUpTestData() {
		channel.setName("hbo");

		transmission1 = TransmissionBuilder.sampleIceAgeBuilder().build();
		transmission2 = TransmissionBuilder.sampleShawshankBuilder().id(1L).build();
		transmission3 = TransmissionBuilder.sampleShawshankBuilder().id(2L).name("Finding Dory").build();

		TransmissionOccurrenceBuilder builder = new TransmissionOccurrenceBuilder()
				.transmission(transmission2).channel(channel);
		occurrence1 = builder.startDate(LocalDateTime.now().plusDays(54)).build();
		occurrence2 = builder.id(1L).startDate(LocalDateTime.now().plusDays(12)).build();
		occurrence3 = builder.id(2L).startDate(LocalDateTime.now().plusDays(5)).build();
		transmission2.setOccurrences(createList(occurrence2, occurrence3));
		transmission1.setOccurrences(createList(occurrence1));

		voting1 = VotingBuilder.sampleLowVotingBuilder().build();
		voting2 = VotingBuilder.sampleMediumVotingBuilder().build();
		voting3 = VotingBuilder.sampleHighVotingBuilder().build();
		transmission2.setVoting(createList(voting1, voting2, voting3));

		user1 = UserBuilder.sampleAdultUserBuilder().build();
		user2 = UserBuilder.sampleYoungUserBuilder().build();
		user3 = UserBuilder.sampleAdminBuilder().build();
	}

	@Test
	public void followTransmissionTest(){
		favoriteTransmissionsService.followTransmission(transmission1, user1);
		assertThat(user1.getFavoriteTransmissions()).contains(transmission1);
	}

	@Test
	public void unfollowTransmissionTest(){
		favoriteTransmissionsService.followTransmission(transmission2, user1);
		favoriteTransmissionsService.unfollowTransmission(transmission2, user1);
		assertThat(user1.getFavoriteTransmissions()).doesNotContain(transmission2);
	}

	@Test
	public void getFavoriteTransmissionsByUserTest(){
		favoriteTransmissionsService.followTransmission(transmission1, user1);
		favoriteTransmissionsService.followTransmission(transmission2, user1);
		favoriteTransmissionsService.followTransmission(transmission3, user1);
		assertThat(user1.getFavoriteTransmissions()).containsExactlyInAnyOrder(transmission1,transmission2,transmission3);
	}

	@Test
	public void getUpcomingFavoriteTransmissionsByUserTest(){
		favoriteTransmissionsService.followTransmission(transmission1, user1);
		favoriteTransmissionsService.followTransmission(transmission2, user1);
		assertThat(favoriteTransmissionsService.getUpcomingFavoriteTransmissionsByUser(user1, Duration.ofDays(25)))
				.containsExactly(transmission2);
	}
}
