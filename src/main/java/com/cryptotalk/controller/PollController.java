package com.cryptotalk.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.cryptotalk.poll.domain.Poll;
import com.cryptotalk.poll.repository.PollRepository;

@RestController
public class PollController {
	@Autowired
	private PollRepository pollRepository;

	private static final Logger LOG = LogManager.getLogger(PollController.class);

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls() {
		List<Poll> pollList = Collections.emptyList();
		try {
			pollList = pollRepository.findAll();
		} catch (Exception ex) {
			LOG.debug("[PollController][PollRepository]Error in getting polls", ex);
		}
		return new ResponseEntity<>(pollList, HttpStatus.OK);
	}

	@RequestMapping(value = "/polls", method = RequestMethod.POST)
	public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
		try {
			poll = pollRepository.save(poll);
		} catch (Exception ex) {
			LOG.debug("[PollController][PollRepository]Error in saving polls", ex);
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		URI pollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId())
				.toUri();
		responseHeaders.setLocation(pollUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
	public ResponseEntity<Poll> getPoll(@PathVariable Long pollId) {
		Poll poll = null;
		try {
			poll = pollRepository.findOne(pollId);
			if (poll == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			LOG.debug("[PollController][PollRepository]Error in getting single poll", ex);
		}
		return new ResponseEntity<>(poll, HttpStatus.OK);
	}

	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
		Poll currentPoll = null;
		try {
			currentPoll = pollRepository.findOne(pollId);
			if (currentPoll == null) {
				return new ResponseEntity<Poll>(HttpStatus.NOT_FOUND);
			}
			currentPoll.setOptions(poll.getOptions());
			currentPoll.setQuestion(poll.getQuestion());
			pollRepository.save(currentPoll);
		} catch (Exception ex) {
			LOG.debug("[PollController][PollRepository]Error in updating single poll", ex);
		}
		return new ResponseEntity<Poll>(currentPoll, HttpStatus.OK);
	}

	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
	public ResponseEntity<Poll> deletePoll(@PathVariable Long pollId) {
		try {
			pollRepository.delete(pollId);
		} catch (Exception ex) {
			LOG.debug("[PollController][PollRepository]Error in deleting single poll", ex);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
