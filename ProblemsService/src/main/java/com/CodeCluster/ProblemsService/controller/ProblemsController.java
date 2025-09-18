package com.CodeCluster.ProblemsService.controller;

import com.CodeCluster.ProblemsService.dto.ProblemDTO;
import com.CodeCluster.ProblemsService.dto.ProblemSetDTO;
import com.CodeCluster.ProblemsService.service.ProblemsControllerService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;


@RestController
public class ProblemsController {

    ProblemsControllerService problemsControllerService;

    public ProblemsController(ProblemsControllerService problemsControllerService) {
        this.problemsControllerService = problemsControllerService;
    }

    @GetMapping("problems")
    public ResponseEntity<List<ProblemSetDTO>> getProblems(@RequestParam(name = "from") int from){

        return ResponseEntity.ok(problemsControllerService.getManyProblems(from));
    }

    @GetMapping("problem/{problemName}")
    public ResponseEntity<ProblemDTO> getProblemWithId(@PathVariable String problemName){

        Optional<ProblemDTO> dto = problemsControllerService.getOneProblem(problemName);

        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
