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

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println(refMember.getClass());
            Member findMember = em.find(Member.class, member.getId());
            System.out.println(findMember.getClass());

            System.out.println("is same member? " + (findMember == refMember));
            System.out.println(refMember.getName());

//            tx.commit();
            em.detach(refMember);

            System.out.println(refMember.getName());

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
