package com.cryptotalk.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryptotalk.poll.domain.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {

}
