package com.web.repository;

import com.web.domain.Keyword;
import com.web.domain.Rank;
import com.web.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    Page<Keyword> findByUser(Pageable pageable, User user);

    @Query(value = "select new com.web.domain.Rank(t.text, count(t) as cnt) from Keyword t group by t.text order by count(t) desc")
    Page<Rank> findTop10(Pageable pageable);
}
