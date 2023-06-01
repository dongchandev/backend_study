package notice.board.notice.boardspring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="member")

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id", nullable = false)
    private Long memberId;
    @Column(name="user_id", nullable = false)
    private String userId;
    @Column(name="pw",nullable = false)
    private String pw;
    @Column(name="name",nullable = false)
    private String name;

    public Member(MemberForm memberForm) {
        this.userId = memberForm.getId();
        this.pw = memberForm.getPw();
        this.name = memberForm.getName();
    }
}
