package com.hcpark.tobyspring6.data;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.JdbcClient;

import com.hcpark.tobyspring6.order.Order;
import com.hcpark.tobyspring6.order.OrderRepository;
import jakarta.annotation.PostConstruct;

public class JdbcOrderRepository implements OrderRepository {

    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    @PostConstruct
    void initDB() {
        jdbcClient.sql("""
                create table orders (id bigint not null, no varchar(255), total number(38,2), primary key (id));
                create sequence orders_SEQ start with 1 increment by 1
            """).update();
    }

    @Override
    public Order save(Order order) {
        var id = jdbcClient.sql("select next value for orders_SEQ").query(Long.class).single();
        System.out.println("New id : " + id);

        order.setId(id);

        jdbcClient.sql("insert into orders (no,total,id) values (?,?,?)")
            .params(order.getNo(), order.getTotal(), order.getId())
            .update();

        return null;
    }

    @Override
    public Order find(Long id) {
        return jdbcClient.sql("select * from orders where id = ?")
            .param(id)
            .query(Order.class)
            .single();
    }
}
