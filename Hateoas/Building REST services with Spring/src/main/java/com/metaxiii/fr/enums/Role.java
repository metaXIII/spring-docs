package com.metaxiii.fr.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    DEV("développeur"),
    BURGLAR("Burglar"),
    THIEF("thief");

    @Getter
    private final String position;
}
