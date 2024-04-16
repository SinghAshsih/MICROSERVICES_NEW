package com.lcwd.rating.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("user_ratings")
public class Rating {
	@Id
	private String ratingId;
	private String userId;
	private String hotelId;
	private String rating;
	private String feedback;

}
