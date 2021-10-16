package com.serendevity;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends PagingAndSortingRepository<Card, Long> {
    List<Card> findByCardNumber(@Param("cardNumber") String cardNumber);
}
