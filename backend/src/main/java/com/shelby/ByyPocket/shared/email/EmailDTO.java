package com.shelby.ByyPocket.shared.email;

import lombok.Data;

@Data
public class EmailDTO {
    private String recipient;
    private String body;
    private String subject;
}
