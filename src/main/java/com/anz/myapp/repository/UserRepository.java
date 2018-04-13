/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/repository/EntityRepository.java.e.vm
 */
package com.anz.myapp.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.*;

import com.anz.myapp.domain.User;
import com.anz.myapp.domain.User_;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Return the persistent instance of {@link User} with the given unique property value login,
     * or null if there is no such persistent instance.
     *
     * @param login the unique value
     * @return the corresponding {@link User} persistent instance or null
     */
    User getByLogin(String login);

    default List<User> complete(String query, int maxResults) {
        User probe = new User();
        probe.setLogin(query);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(User_.login.getName(), match -> match.ignoreCase().startsWith());

        Page<User> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }
}