package com.toy.truffle.divide.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.math.BigDecimal;

@Entity
@Table(name = "expense")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenseSeq;

	@Column(nullable = false)
	private Long travelSeq;

	@Column(length = 50)
	private String placeName;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private String planDate;

	private String paymentMethod;

	@Column(nullable = false)
	private Integer price;

	@Column(length = 50)
	private String expenseName;

	@Column(length = 1, nullable = false)
	private String privateYn;

	@Column(length = 10)
	private String mapId;

	@Column(precision = 10, scale = 6)
	private BigDecimal latitude;

	@Column(precision = 10, scale = 6)
	private BigDecimal longitude;

	private Long fileMappingSeq;

	@Column(length = 20)
	private String createUserId;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private String createDttm;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private String updateDttm;

}

