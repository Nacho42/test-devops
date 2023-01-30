package com.opentrends.apibuilder.adapters.outbound.persistence.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beans")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "externalId")
public class BeanEntity {
    @Id
    @Column
    private Integer externalId;

    @Column
    private String name;

    @Column
    private String repositoryPath;

    @Column
    private String version;
}
