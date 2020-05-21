package com.training.carwash.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Customer {
  @Id
  @Column(name = "customerId")
  @GeneratedValue
  private Long customerId;
  private String name;
  private String address;
  private String city;
  private String phoneNumber;
  private String active;

}
