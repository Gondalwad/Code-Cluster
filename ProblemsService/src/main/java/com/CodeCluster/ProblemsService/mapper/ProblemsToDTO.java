package com.CodeCluster.ProblemsService.mapper;

import com.CodeCluster.ProblemsService.dto.ProblemDTO;
import com.CodeCluster.ProblemsService.dto.ProblemSetDTO;
import com.CodeCluster.ProblemsService.model.ProblemsTable;

import java.util.ArrayList;
import java.util.List;

public class ProblemsToDTO {

    public static ProblemDTO toProblemDTO(ProblemsTable problemsTable) {
        ProblemDTO problemDTO = new ProblemDTO();

        problemDTO.setProblemId(problemsTable.getProblemId().toString());
        problemDTO.setProblemNo(problemsTable.getProblemNo());
        problemDTO.setDifficulty(problemsTable.getDifficultyLevel());
        problemDTO.setProblemName(problemsTable.getProblemName());
        problemDTO.setProblemDescription(problemsTable.getProblemDescription());
        problemDTO.setProblemCodeSnippet(problemsTable.getJavaSnippet());

        return problemDTO;
    }

    public static List<ProblemSetDTO> toProblemSetDTO(List<ProblemsTable> list){
        List<ProblemSetDTO> dto = new ArrayList<>();

        for(ProblemsTable p : list){
            ProblemSetDTO pst = new ProblemSetDTO();

            pst.setProblemNo(p.getProblemNo());
            pst.setProblemId(p.getProblemId().toString());
            pst.setDifficulty(p.getDifficultyLevel());
            pst.setProblemName(p.getProblemName());
            pst.setProblemDescription(p.getProblemDescription());
            /// adds to list
            dto.add(pst);
        }

        return dto;

    }
}
