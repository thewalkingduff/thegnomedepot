package com.devduffy.gnomedepot.dto;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.devduffy.gnomedepot.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

        private Integer id;
        private UserDTO userDTO;
        private Date orderDate;
        private String status;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date shippedDate;

        public Order toModel () {
            return new Order(id, userDTO.toModel(), orderDate, status, shippedDate);
        }   
}
