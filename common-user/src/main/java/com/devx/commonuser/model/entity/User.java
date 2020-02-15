package com.devx.commonuser.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence_gen")
  @SequenceGenerator(name = "user_id_sequence_gen", sequenceName = "user_id_sequence")
  private Integer id;

  @Column(unique = true, length = 20, name = "username")
  private String username;

  @Column(length = 60, name = "password")
  private String password;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "name")
  private String name;

  @Column(name = "last_name")
  private String lastName;

  @Column(unique = true, length = 100, name = "email")
  private String email;

  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
      @UniqueConstraint(columnNames = {"user_id", "role_id"})})
  private List<Role> roles;


}
