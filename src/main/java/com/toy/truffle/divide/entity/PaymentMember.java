package com.toy.truffle.divide.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "payment_member")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(PaymentMemberPK.class)
public class PaymentMember {

	@Id
	@Column(nullable = false)
	private Long expenseSeq;

	@Id
	@Column(nullable = false)
	private Long travelSeq;

	@Id
	@Column(length = 20, nullable = false)
	private String userId;

	@Column(length = 1, nullable = false)
	private String paymentYn;

	@Column(length = 1, nullable = false)
	private String repayYn;
}

