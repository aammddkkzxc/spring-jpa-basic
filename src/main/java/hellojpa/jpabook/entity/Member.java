package hellojpa.jpabook.entity;

import hellojpa.jpabook.valuetype.Address;
import hellojpa.jpabook.valuetype.Period;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {
//
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    private String name;

    @ElementCollection
    @CollectionTable(name = "food", joinColumns = @JoinColumn(name = "member_id"))
    private Set<String> foods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "address", joinColumns = @JoinColumn(name = "member_id"))
    private List<Address> addressList = new ArrayList<>();

    @Embedded
    private Period period;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Set<String> getFoods() {
        return foods;
    }

    public void setFoods(Set<String> foods) {
        this.foods = foods;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
