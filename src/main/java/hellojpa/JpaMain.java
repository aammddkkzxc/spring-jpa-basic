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

            Order order = new Order();
            order.setOrderStatus(OrderStatus.ORDER);
            order.setMember(member);

            em.persist(member);
            em.persist(order);

            em.flush();
            em.clear();

//            em.find(Order.class, order.getId());
            List<Order> orders = em.createQuery("select o from Order o", Order.class).getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
