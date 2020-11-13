package webquiz.engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AnsweredQuizzes {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long answeredId;

    @JsonIgnore
    private String user;

    @JsonIgnore
    private Boolean flag;

    private LocalDateTime completedAt;
    private Long id;

    public AnsweredQuizzes(){}

    public AnsweredQuizzes(String user, Long id, LocalDateTime completedAt, Boolean flag) {
        this.user = user;
        this.completedAt = completedAt;
        this.id = id;
        this.flag = flag;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long quizId) {
        this.id = id;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public long getAnsweredId() {
        return answeredId;
    }

    public void setAnsweredId(long id) {
        this.id = answeredId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
