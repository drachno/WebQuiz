package webquiz.engine.repository;

import webquiz.engine.entity.AnsweredQuizzes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AnsweredQuizzesRepository extends PagingAndSortingRepository<AnsweredQuizzes, Long> {

    @Query(value = "SELECT * FROM Answered_Quizzes WHERE user = :user and flag = true", nativeQuery = true)
    Page<AnsweredQuizzes> getAnsweredQuizzes(@Param("user") String owner, Pageable paging);

}
