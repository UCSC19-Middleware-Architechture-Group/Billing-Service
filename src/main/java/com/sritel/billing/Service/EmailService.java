package com.sritel.billing.Service;

import com.sritel.billing.Repository.PaymentHistoryRepository;
import com.sritel.billing.event.MonthlyStatementEvent;
import com.sritel.billing.event.StatementRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmailService {
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final KafkaTemplate<String, MonthlyStatementEvent> kafkaTemplate;

    public void sendMonthlyStatement(String toEmail, Long userId) throws MessagingException, IOException, jakarta.mail.MessagingException {

        MonthlyStatementEvent monthlyStatementEvent = new MonthlyStatementEvent();
        monthlyStatementEvent.setEmail(toEmail);
        monthlyStatementEvent.setFirstName("Buddhika");
        monthlyStatementEvent.setLastName("Senanayake");
        monthlyStatementEvent.setYear("2024");
        monthlyStatementEvent.setMonth("September");

        List<StatementRecord> statementRecordList = paymentHistoryRepository.findByUserId(userId)
                .stream()
                .map(record -> {
                    StatementRecord statementRecord = new StatementRecord();
                    statementRecord.setAmount(record.getAmount().toBigInteger().doubleValue());
                    statementRecord.setPaymentDate(record.getPaymentDate().toString());
                    return statementRecord;
                })
                .toList();


        monthlyStatementEvent.setStatementRecords(statementRecordList);

        log.info("Start - Sending MonthlyStatementEvent {} to kafka topic monthly-statement", monthlyStatementEvent);
        kafkaTemplate.send("monthly-statement", monthlyStatementEvent);
        log.info("End - Sending MonthlyStatementEvent {} to kafka topic monthly-statement", monthlyStatementEvent);

    }
}
