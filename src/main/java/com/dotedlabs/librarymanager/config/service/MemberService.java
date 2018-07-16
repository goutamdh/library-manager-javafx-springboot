package com.dotedlabs.librarymanager.config.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotedlabs.librarymanager.config.entity.Member;
import com.dotedlabs.librarymanager.config.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	
	/**
	 * Add new member
	 * 
	 * @param member
	 * @return
	 */
	public Member addMember(Member member) {
		return memberRepository.save(member);
	}
	
	/**
	 * Find all members
	 * 
	 * @return
	 */
	public List<Member> findAll() {
		return memberRepository.findAll();
	}
	
	/**
	 * Find details of a member
	 * 
	 * @param bookId
	 * @return
	 */
	public Member findById(UUID memberId) {
		return memberRepository.findOne(memberId);
	}
}
