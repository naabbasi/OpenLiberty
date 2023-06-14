package edu.learn.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NamedQueries(@NamedQuery(name = "ALL_CUSTOMER", query = "select customer from Customer customer "))
public class Customer {
    public static final String ALL_CUSTOMER = "ALL_CUSTOMER";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gkey")
    private Long genericKey;
    @Column(name = "customer_name")
    private String customerName;
}
