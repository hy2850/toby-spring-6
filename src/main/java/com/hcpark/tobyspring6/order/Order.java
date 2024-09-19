package com.hcpark.tobyspring6.order;

import java.math.BigDecimal;

//@Entity
//@Table(name = "orders")
public class Order {

//    @Id
//    @GeneratedValue
    private Long id;

//    @Column(unique = true)
    private String no;

    private BigDecimal total;

    // JPA에서, 파라미터가 있는 생성자 만들려면, 파라미터가 없는 기본 생성자도 생성해줘야 함
    public Order() {
    }

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", no='" + no + '\'' +
            ", total=" + total +
            '}';
    }
}
