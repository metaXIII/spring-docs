package com.metaxiii.fr.dto;

import com.metaxiii.fr.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @NotNull(message = "status could not be null or empty")
    private Status status;
}
