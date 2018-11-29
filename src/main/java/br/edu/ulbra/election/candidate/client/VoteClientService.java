package br.edu.ulbra.election.candidate.client;

import br.edu.ulbra.election.candidate.output.v1.VoteOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class VoteClientService {

    private final VoteClient voteClient;

    @Autowired
    public VoteClientService(VoteClient voteClient){
        this.voteClient = voteClient;
    }

    public VoteOutput getById(Long id){
        return this.voteClient.getById(id);
    }

    public Boolean getByElectionAndCandidate(Long electionId, Long candidateId){
        return this.voteClient.getByElectionAndCandidate(electionId,candidateId);
    }

    @FeignClient(value="vote-service", url="${url.vote-service}")
    private interface VoteClient {

        @GetMapping("/v1/vote/{voteId}")
        VoteOutput getById(@PathVariable(name = "voteId") Long partyId);

        @GetMapping("/v1/vote/byElectionAndCandidateId/{electionId,candidateId}")
        Boolean getByElectionAndCandidate (@PathVariable(name="electionId") Long electionId, @PathVariable(name="candidateId") Long candidateId );
    }


}
