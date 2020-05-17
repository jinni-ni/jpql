import jpql.Member;
import jpql.MemberDTO;
import jpql.MemberType;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m.username, 'HELLO', TRUE From Member m " +
                            "where m.type = :userType";

            List<Object[]> result = em.createQuery(query)
                    .setParameter("userType",MemberType.ADMIN)
                    .getResultList();
            for(Object[] objects : result){
                System.out.println("objects = " + objects[0]);
                System.out.println("objects = " + objects[1]);
                System.out.println("objects = " + objects[2]);

            }

//
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc",Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(10)
//                    .getResultList();
//            for(Member member1:result){
//                System.out.println("member1 = " + member1);
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

//
//public class JpaMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);
//
//            List<MemberDTO> resultLists = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//            MemberDTO memberDTO = resultLists.get(0);
//            System.out.println("memberDTO = "+memberDTO.getUsername());
//            System.out.println("memberDTO = "+memberDTO.getAge());
//            em.flush();
//            em.clear();
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//
//        emf.close();
//    }
//}


//
//public class JpaMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);
//
//            TypedQuery<Member> query1 = em.createQuery("select m from Member m",Member.class);
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m",String.class);
//            Query query3 = em.createQuery("select m.username, m.age from Member m");
//
//            TypedQuery<Member> query = em.createQuery("select m from Member m",Member.class);
//            List<Member> resultList = query.getResultList();
//
//            TypedQuery<Member> query5 = em.createQuery("select m from Member m where m.id= 10L",Member.class);
//            // getSingleReuslt()
//            // 결과 0, 두개 이상 exception 발생
//            Member result = query.getSingleResult();
//            System.out.println("reuslt = "+result);
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//
//        emf.close();
//    }
//}
//
