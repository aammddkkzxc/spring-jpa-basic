package hellojpa;

import hellojpa.jpabook.cascadepractice.Child;
import hellojpa.jpabook.cascadepractice.Parent;
import hellojpa.jpabook.entity.Member;
import hellojpa.jpabook.entity.Order;
import hellojpa.jpabook.entity.OrderStatus;
import hellojpa.jpabook.valuetype.Address;
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
            member.setName("mem1");

            member.getFoods().add("피자");
            member.getFoods().add("치킨");

            member.getAddressList().add(new Address("hi", "hello", "안녕"));
            member.getAddressList().add(new Address("hi2", "hello2", "안녕2"));

            em.persist(member);
            em.flush();
            em.clear();

            //equals비교 통해서 삭제함
            Member member1 = em.find(Member.class, member.getId());
            member1.getAddressList().remove(new Address("hi", "hello", "안녕"));
            em.persist(member1);

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
