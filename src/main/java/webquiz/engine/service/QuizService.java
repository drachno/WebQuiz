package webquiz.engine.service;

import webquiz.engine.entity.AnswerWrapper;
import webquiz.engine.entity.AnsweredQuizzes;
import webquiz.engine.entity.Quiz;
import webquiz.engine.entity.ResponseStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface QuizService {

    Quiz addNewQuiz(Quiz quiz, Authentication authentication);

    Page<Quiz> getAllQuizzes(Integer page, Integer pageSize);

    Page<AnsweredQuizzes> getAnsweredQuizzes(Authentication authentication, Integer pageNo, Integer pageSize);

    Quiz getQuizNullSafe(long id);

    ResponseStatus checkAnswer(long id, AnswerWrapper answer, Authentication authentication);

    ResponseEntity<?> deleteQuiz(Long id, Authentication authentication);

}
