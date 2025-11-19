package study.jpa.jpabook.model.puremapping;

public class ExamMain {

    public static void main(String[] args) {
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");

        Team team = new Team("team1", "팀1");
        member1.setTeam(team);
        member2.setTeam(team);

        Team findTeam = member1.getTeam();

        System.out.println(findTeam);
    }



}
