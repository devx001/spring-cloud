package com.devx.commonuser.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_sequence_gen")
  @SequenceGenerator(name = "role_id_sequence_gen", sequenceName = "role_id_sequence")
  private Integer id;

  @Column(unique = true, name = "name")
  private String name;

}
