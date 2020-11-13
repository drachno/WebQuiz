package webquiz.engine.repository;

import webquiz.engine.entity.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizRepository extends PagingAndSortingRepository<Quiz, Long> {

    Quiz findById(long id);
}