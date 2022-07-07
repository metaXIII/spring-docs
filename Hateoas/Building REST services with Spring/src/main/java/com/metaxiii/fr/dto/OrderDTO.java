package com.metaxiii.fr.dto;

import com.metaxiii.fr.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Status status;
}
