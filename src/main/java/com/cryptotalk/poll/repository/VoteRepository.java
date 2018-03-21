package com.cryptotalk.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryptotalk.poll.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
