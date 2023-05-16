package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;
    public JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        //SimpleJdbcInsert : 간단하게 데이터를 저장하기 위해 만들어진 구현체
        // insert할 테이블과 auto generated key 명시
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        // key - value
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        // 테이블 이름과 컬럼 이름과 값만 있으면 insert문을 만들 수 있듯, 필요한 정보만 SimpleJdbcInsert에 넘겨준다.
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        //매핑 : 키(key) 역할을 하는 데이터와 값(value) 역할을 하는 데이터를 짝 지어(=연결 지어) 저장하는 데이터 구조
        return result.stream().findAny();
        //stream:컬렉션 데이터를 선언형으로 쉽게 처리
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member",memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
            Member member=new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
