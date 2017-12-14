package co.fy.gateway.server.user.service;

import co.fy.gateway.server.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CREATED IN  2017-11-27 上午11:17
 *
 * @AUTHOR: 姜坤
 **/
public interface  UserRepository extends JpaRepository<User, Long> {
        User findOneByUsername(String username);
}