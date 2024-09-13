package com.kibe.QuizApplication.controllers;

import com.kibe.QuizApplication.entity.Question;
import com.kibe.QuizApplication.services.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@Tag(name="Questions API", description="This is the Questions API")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    @Operation(
            summary = "API to get All questions",
            description = "This is just some random text to test",
            responses = {
                    @ApiResponse(
                            description = "All questions"
//                            headers = {
//                                    @Header(name = )
//                            }
                    )
            }
    )
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        if (!questions.isEmpty()) {
            return ResponseEntity.ok(questions);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "API to Get a single question for the specified ID",
            description = "Get a single question for the specified ID",
            parameters = {
                    @Parameter(name = "id", description = "Unique identifier for a question", required = true)
            },
            responses = {
                    @ApiResponse(
                            description = "Single question for the specified ID"
                    )
            }
    )
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id) {
        Question question = questionService.getQuestionById(id);
        if (question != null) {
            return ResponseEntity.ok(question);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping
    @Operation(
            summary = "API to delete a single question for the specified ID",
            description = "Delete a single question for the specified ID",
            parameters = {
                    @Parameter(name = "id", description = "Unique identifier for a question", required = true)
            }
    )
    public ResponseEntity<Question> deleteQuestionById(@RequestParam Integer id) {
        Boolean deleted = questionService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Question> updateQuestionById(@RequestBody Question question, @RequestParam Integer id) {
        Question updatedQuestion = questionService.updateQuestion(id, question);
        if (updatedQuestion != null) {
            return ResponseEntity.ok(updatedQuestion);
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Question>> getQuestionByCategory(@RequestParam String category) {
        List<Question> questions = questionService.getQuestionsByCategory(category);
        if (questions != null) {
            return ResponseEntity.ok(questions);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody Question question) {
        if (question != null) {
            Boolean created = questionService.addQuestion(question);
            if (created) {
                // Return a 201 Created status with a location header
                return ResponseEntity.status(201).body("Created Successfully!");
            }
            return ResponseEntity.badRequest().body("Question could not be created.");
        }
        return ResponseEntity.internalServerError().build();
    }


}
