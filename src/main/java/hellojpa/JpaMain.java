package hellojpa;

import hellojpa.jpabook.entity.Member;
import hellojpa.jpabook.entity.Order;
import hellojpa.jpabook.entity.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("hi");
            em.persist(member);

            Order order = new Order();
            order.setOrderStatus(OrderStatus.ORDER);
            order.setMember(member);
            em.persist(order);

//            member.getOrderList().add(order);

//            em.flush();
//            em.clear();
//            Member findMember = em.find(Member.class, member.getId());
//            List<Order> orders = findMember.getOrderList();
//            for (Order oo : orders) {
//                System.out.println(oo);
//            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
