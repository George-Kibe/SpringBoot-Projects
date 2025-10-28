package com.kibe.QuizApplication.services;

import com.kibe.QuizApplication.dao.QuestionDao;
import com.kibe.QuizApplication.entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {
    private final QuestionDao questionDao;
    @PersistenceContext
    private final EntityManager entityManager;

    public QuestionService(QuestionDao questionDao, EntityManager entityManager) {
        this.questionDao = questionDao;
        this.entityManager = entityManager;
    }

    public String getQuestion() {
        return "One Question Test";
    }

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public Question getQuestionById(Integer id) {
        Question question;
        question = questionDao.findById(id).orElse(null);
        return question;
    }

    public List<Question> getQuestionsByCategory(String category) {
        List<Question> questions;
        questions = questionDao.findByCategory(category);
        if (!questions.isEmpty()) {
            return questions;
        }
        return null;
    }

    public Boolean addQuestion(Question question) {
        Question newQuestion = questionDao.save(question);
        return true;
    }

    public Boolean deleteById(Integer id) {
        if (questionDao.existsById(id)) {
            questionDao.deleteById(id);
            return true;
        }
        return false;
    }

    public Question updateQuestion(Integer id, Question updatedQuestion) {
        Optional<Question> questionOptional = questionDao.findById(id);
        if (questionOptional.isPresent()) {
            Question existingQuestion = questionOptional.get();

            // Update the fields only if they are not null in the updatedQuestion
            if (updatedQuestion.getQuestionTitle() != null) {
                existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
            }
            if (updatedQuestion.getOption1() != null) {
                existingQuestion.setOption1(updatedQuestion.getOption1());
            }
            if (updatedQuestion.getOption2() != null) {
                existingQuestion.setOption2(updatedQuestion.getOption2());
            }
            if (updatedQuestion.getOption3() != null) {
                existingQuestion.setOption3(updatedQuestion.getOption3());
            }
            if (updatedQuestion.getOption4() != null) {
                existingQuestion.setOption4(updatedQuestion.getOption4());
            }
            if (updatedQuestion.getRightAnswer() != null) {
                existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
            }
            if (updatedQuestion.getCategory() != null) {
                existingQuestion.setCategory(updatedQuestion.getCategory());
            }
            if (updatedQuestion.getDifficultyLevel() != null) {
                existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            }

            // Save and return the updated question
            return questionDao.save(existingQuestion);
        }

        // Return null if the question is not found
        return null;
    }
    public List<Question> getRandomQuestionsByCategory(String category, int numQ) {
        String sql = "SELECT * FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ";

        Query query = entityManager.createNativeQuery(sql, Question.class);
        query.setParameter("category", category);
        query.setParameter("numQ", numQ);

        // Execute the query and return the result as a list
        return query.getResultList();
    }
}
