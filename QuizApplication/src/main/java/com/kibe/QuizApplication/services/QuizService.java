package com.kibe.QuizApplication.services;

import com.kibe.QuizApplication.dao.QuestionDao;
import com.kibe.QuizApplication.dao.QuizDao;
import com.kibe.QuizApplication.entity.Question;
import com.kibe.QuizApplication.entity.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizService {
    private final QuizDao quizDao;
    private final QuestionDao questionDao;
    private final QuestionService questionService;

    public QuizService(QuizDao quizDao, QuestionDao questionDao, QuestionService questionService) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
        this.questionService = questionService;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        // List<Question> questions = questionService.getRandomQuestionsByCategory(category, numQ);


        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return ResponseEntity.ok("Quiz created");
    }
}
