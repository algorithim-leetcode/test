package com.toy.truffle.divide.repository;

import com.toy.truffle.divide.dto.DivideDTO;
import com.toy.truffle.divide.entity.PaymentMember;
import com.toy.truffle.divide.entity.PaymentMemberPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DivideRepository extends JpaRepository<PaymentMember, PaymentMemberPK> {

    // 개인별 결제금액 리스트
    @Query(value = "select pm.userId, sum(e.price) as paidAmount " +
                   "  from PaymentMember pm " +
                   "  left join Expense e on pm.expenseSeq = e.expenseSeq " +
                   "   and pm.travelSeq = e.travelSeq " +
                   " where pm.repayYn = 'Y' " +
                   "   and pm.expenseSeq = :travelSeq " +
                   " group by pm.userId ")
    List<DivideDTO> findRepayList(@Param("travelSeq") long travelSeq);

    // 개인별 정산금액 리스트
    @Query(value = "select pm.userId, sum(e.price) as totalPayment " +
            "  from PaymentMember pm " +
            "  left join Expense e on pm.expenseSeq = e.expenseSeq " +
            "   and pm.travelSeq = e.travelSeq " +
            " where pm.paymentYn = 'Y' " +
            "   and pm.expenseSeq = :travelSeq " +
            " group by pm.userId ")
    List<DivideDTO> findPaymentList(@Param("travelSeq") long travelSeq);
}
