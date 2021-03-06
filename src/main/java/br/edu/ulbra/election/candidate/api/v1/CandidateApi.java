package br.edu.ulbra.election.candidate.api.v1;

import br.edu.ulbra.election.candidate.input.v1.CandidateInput;
import br.edu.ulbra.election.candidate.output.v1.CandidateOutput;
import br.edu.ulbra.election.candidate.output.v1.GenericOutput;
import br.edu.ulbra.election.candidate.service.CandidateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/candidate")
public class CandidateApi {

    private final CandidateService candidateService;

    @Autowired
    public CandidateApi(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get candidates List")
    public List<CandidateOutput> getAll(){
        return candidateService.getAll();
    }

    @GetMapping("/election/{electionId}")
    @ApiOperation(value = "Get candidates List by Election")
    public List<CandidateOutput> getByElection(@PathVariable Long electionId){
        return candidateService.getByElection(electionId);
    }

    @GetMapping("/party/{partyId}")
    @ApiOperation(value = "Get candidates List by Party")
    public List<CandidateOutput> getByParty(@PathVariable Long partyId){
        return candidateService.getByParty(partyId);
    }

    @GetMapping("/{candidateId}")
    @ApiOperation(value = "Get candidate by Id")
    public CandidateOutput getById(@PathVariable Long candidateId){
        return candidateService.getById(candidateId);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create new candidate")
    public CandidateOutput create(@RequestBody CandidateInput candidateInput){
        return candidateService.create(candidateInput);
    }

    @PutMapping("/{candidateId}")
    @ApiOperation(value = "Update candidate")
    public CandidateOutput update(@PathVariable Long candidateId, @RequestBody CandidateInput candidateInput){
        return candidateService.update(candidateId, candidateInput);
    }

    @DeleteMapping("/{candidateId}")
    @ApiOperation(value = "Delete candidate")
    public GenericOutput delete(@PathVariable Long candidateId){
        return candidateService.delete(candidateId);
    }

    @GetMapping("/getByNumberAndElection/{electionId}/{candidateNumber}")
    @ApiOperation(value = "Get candidate by Election and Candidate Number")
    public CandidateOutput getByNumberAndElection(@PathVariable(name = "electionId") Long electionId, @PathVariable(name = "candidateNumber") Long candidateNumber){
        return candidateService.getByNumberAndElection(electionId, candidateNumber);
    }


}
