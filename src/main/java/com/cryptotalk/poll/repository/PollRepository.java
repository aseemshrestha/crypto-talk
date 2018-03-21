package com.cryptotalk.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryptotalk.poll.domain.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {

}