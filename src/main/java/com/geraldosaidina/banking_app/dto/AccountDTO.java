package com.geraldosaidina.banking_app.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class AccountDTO {
    private Long id;

    private String account_owner;

    private double balance;
}
