package com.metaxiii.fr.entity;

import com.metaxiii.fr.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CUSTOMER_ORDER")
public class Order {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue
    private UUID id;

    private String description;

    private Status status;
}
