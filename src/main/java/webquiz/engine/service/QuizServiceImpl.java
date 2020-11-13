package webquiz.engine.service;

import webquiz.engine.entity.AnswerWrapper;
import webquiz.engine.entity.AnsweredQuizzes;
import webquiz.engine.entity.Quiz;
import webquiz.engine.entity.ResponseStatus;
import webquiz.engine.exceptions.QuizNotFoundException;
import webquiz.engine.repository.AnsweredQuizzesRepository;
import webquiz.engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;


@Service
public class QuizServiceImpl implements QuizService {

    private final String SUCCESS_MESSAGE = "Congratulations, you are right!";
    private final String FAIL_MESSAGE = "Wrong answer! Please, try again.";
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private AnsweredQuizzesRepository answeredQuizzesRepository;
    @Autowired
    private UserService userService;

    @Override
    public Quiz addNewQuiz(Quiz quiz, Authentication authentication) {
        quiz.setCreator(authentication.getName());
        return quizRepository.save(quiz);
    }

    @Override
    public Page<Quiz> getAllQuizzes(Integer page, Integer pageSize) {
        return quizRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Page<AnsweredQuizzes> getAnsweredQuizzes(Authentication authentication, Integer pageNo, Integer pageSize) {
        return answeredQuizzesRepository.getAnsweredQuizzes(authentication.getName(), PageRequest.of(pageNo, pageSize, Sort.by("completed_At").descending()));
    }

    @Override
    public Quiz getQuizNullSafe(long id) {
        Quiz quiz = quizRepository.findById(id);
        if (quiz == null) {
            throw new QuizNotFoundException();
        }
        return quiz;
    }

    @Override
    public ResponseStatus checkAnswer(long id, AnswerWrapper answer, Authentication authentication) {
        Quiz quiz = quizRepository.findById(id);
        if (Arrays.equals(quiz.getAnswers(), answer.getAnswers())) {
            quiz.setFlag(true);
            quiz.setAnsweredBy(authentication.getName());
            answeredQuizzesRepository.save(new AnsweredQuizzes(authentication.getName(), quiz.getId(), LocalDateTime.now(), true));
            return new ResponseStatus(true, SUCCESS_MESSAGE);
        }
        return new ResponseStatus(false, FAIL_MESSAGE);
    }

    @Override
    public ResponseEntity<?> deleteQuiz(Long id, Authentication authentication) {
        Quiz quiz = getQuizNullSafe(id);
        if (quiz.getCreator().equals(authentication.getName())) {
            quizRepository.delete(quiz);
            return new ResponseEntity<>(quiz, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quiz, HttpStatus.FORBIDDEN);
    }
}