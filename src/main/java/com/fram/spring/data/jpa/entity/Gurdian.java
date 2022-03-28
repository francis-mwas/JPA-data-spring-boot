package com.fram.spring.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name="name",
                column = @Column(name = "gurdian_name")
        ),
        @AttributeOverride(
                name="email",
                column = @Column(name = "gurdian_email")
        ),
        @AttributeOverride(
                name="mobile",
                column = @Column(name = "gurdian_mobile")
        )
})
public class Gurdian {

    private String name;
    private String email;
    private String mobile;

}
