package com.toy.truffle.divide.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PaymentMemberPK implements Serializable {

    private Long expenseSeq;

    private Long travelSeq;

    private String userId;
}
