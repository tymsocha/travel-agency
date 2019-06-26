package com.kodilla.travelagency.core.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity(name = "hotels")
public class Hotel {
}
