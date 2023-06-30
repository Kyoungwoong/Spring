package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        /*
         * 실제 비즈니스 로직. DB에 데이터를 넣는다던가 가져온다던가
         */
        // tx생성. DB 관련 작업들은 모두 tx를 생성해야함.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // JPA만 동작할때는 코드를 아래와 같이 짜야하지만. Spring이 이를 다 처리해줌.
        try {
            // 회원 생성.
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("helloA");
//            // DB에 저장
//            em.persist(member);

            // 회원 조회.
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getName( = " + findMember.getName());
//            System.out.println("findMember.setName( = " + findMember.getId());
//          // 회원 삭제.
//            em.remove(findMember);

            // 회원 수정. 저장 안해도됨. UPDATE 쿼리가 나감.
//            findMember.setName("HelloJpa");
            // 객체를 가지고 오면 JPA가 관리.
            // JPA가 변경사항을 체크하고 UPDATE 쿼리를 날림.

            // JPQL
            // 객체 지향 쿼리.
            List<Member> findMembers = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            for (Member findMember : findMembers) {
                System.out.println("findMember.getName( = " + findMember.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 매우 중요한 동작. database connection을 물고 동작.
            // 꼭 닫아야함.
            em.close();
        }

        emf.close();
    }
}
