package org.exam.service.Impl;

import lombok.AllArgsConstructor;
import org.exam.common.exception.user.NoExistUserException;
import org.exam.domain.User;
import org.exam.repository.UserRepository;
import org.exam.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByIdx(Long idx) {
        return userRepository.findFirstByIdx(idx).orElseThrow(() -> new NoExistUserException());
    }
}
