package webquiz.engine.controllers;

import webquiz.engine.entity.AnswerWrapper;
import webquiz.engine.entity.ResponseStatus;
import webquiz.engine.entity.AnsweredQuizzes;
import webquiz.engine.entity.Quiz;
import webquiz.engine.service.QuizServiceImpl;
import webquiz.engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@RestController
public class QuizController {

    @Autowired
    QuizServiceImpl quizService;

    @Autowired
    UserService userService;

    @PostMapping(path = "/api/quizzes")
    public Quiz addQuizz(@RequestBody @Valid @NotNull Quiz quiz, Authentication authentication) {
        return quizService.addNewQuiz(quiz, authentication);
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Quiz getQuizById(@PathVariable long id) {
        return quizService.getQuizNullSafe(id);
    }

    @GetMapping(path = "/api/quizzes")
    public Page<Quiz> getQuizzes(@RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
                                 @RequestParam(defaultValue = "10") @Min(1) Integer pageSize) {
        return quizService.getAllQuizzes(page, pageSize);
    }

    @GetMapping(path = "/api/quizzes/completed")
    public Page<AnsweredQuizzes> getAnswered(@RequestParam(defaultValue = "0", required = false) @Min(0) Integer page,
                                             @RequestParam(defaultValue = "10", required = false) @Min(1) Integer pageSize,
                                             Authentication authentication) {
        return quizService.getAnsweredQuizzes(authentication, page, pageSize);
    }

    @PostMapping(path = "api/quizzes/{id}/solve", consumes = "application/json")
    public ResponseStatus checkAnswer(@PathVariable long id, @RequestBody(required = false) AnswerWrapper answer, Authentication authentication) {
        return quizService.checkAnswer(id, answer, authentication);

    }

    @DeleteMapping(path = "/api/quizzes/{id}")
    public ResponseEntity deleteQuiz(@PathVariable Long id, Authentication authentication) {
        return quizService.deleteQuiz(id, authentication);
    }

}
