package com.devduffy.gnomedepot.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "order_date")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date orderDate;

        @Column(name = "status", nullable = false)
        private String status;

        @Column(name = "shipped_date")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date shippedDate;

        @ToString.Exclude
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "users_id", nullable = false)
        private User user;

        @ToString.Exclude
        @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
        @JoinTable(name = "orderdetails", joinColumns = {
                        @JoinColumn(name = "orders_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
                                        @JoinColumn(name = "products_id", referencedColumnName = "id", nullable = false, updatable = false) })
        private Set<Product> products = new HashSet<>();

}