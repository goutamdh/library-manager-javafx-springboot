package com.dotedlabs.librarymanager.config.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dotedlabs.librarymanager.config.entity.Member;

public interface MemberRepository extends JpaRepository<Member, UUID> {

}
