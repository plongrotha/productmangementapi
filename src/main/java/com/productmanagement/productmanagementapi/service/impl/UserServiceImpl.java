package com.productmanagement.productmanagementapi.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;
import com.productmanagement.productmanagementapi.exception.InvalidException;
import com.productmanagement.productmanagementapi.exception.NotFoundException;
import com.productmanagement.productmanagementapi.exception.ResourceAlreadyExistException;
import com.productmanagement.productmanagementapi.model.entity.User;
import com.productmanagement.productmanagementapi.repository.UserRepository;
import com.productmanagement.productmanagementapi.service.UserService;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        Random random = new Random();
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistException("email : " + user.getEmail() + "already existed.");
        }
        user.setFullName(user.getFirstName() + user.getLastName());
        user.setUserName((user.getFirstName() + user.getLastName() + random.nextInt(100)).toLowerCase());
        user.setPassword(generateRandomPassword(random));
        if (user.getDateOfBirth() != null) {
            user.setAge(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears());
        } else {
            throw new InvalidException("date of birth cannot be null");
        }
        System.out.println("date of birth {} :" + user.getDateOfBirth());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public List<User> createUsersBulk(List<User> users) {
        if (users == null || users.isEmpty()) {
            throw new NotFoundException("no user to insert");
        }
        for (User user : users) {
            if (StringUtils.isNotEmpty(user.getFirstName()) && StringUtils.isNotEmpty(user.getLastName())) {
                user.setFirstName(user.getFirstName());
                user.setLastName(user.getLastName());
                user.setFullName(user.getFirstName() + " " + user.getLastName());
                user.setUserName(user.getFirstName() + user.getLastName() + new Random().nextInt(100));
            }
            if (user.getDateOfBirth() != null) {
                user.setAge(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears());
            }
            if (StringUtils.isNotEmpty(user.getPassword())) {
                user.setPassword(generateRandomPassword(new Random()));
            }
        }
        return userRepository.saveAll(users);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found with id : " + id));
    }

    @Override
    public User updateUserById(long id, User user) {
        User existed = getUserById(id);
        existed.setFirstName(user.getFirstName());
        existed.setLastName(user.getLastName());
        existed.setFullName(existed.getFirstName() + " " + existed.getLastName());
        existed.setPhoneNumber(user.getPhoneNumber());
        existed.setDateOfBirth(user.getDateOfBirth());
        existed.setAge(Period.between(existed.getDateOfBirth(), LocalDate.now()).getYears());
        return userRepository.save(existed);
    }

    @Override
    public List<User> getAllUsers() {
        return Optional.of(userRepository.findAll()).filter(user -> !user.isEmpty())
                .orElseThrow(() -> new NotFoundException("no user have found"));
    }

    @Override
    public void deleteUserById(long id) {
        getUserById(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<String> getAllEmails() {
        return userRepository.findAllEmail().orElseThrow(() -> new NotFoundException("email is not found"));
    }

    // generate password
    private String generateRandomPassword(Random random) {
        String chars = "0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    // @PostConstruct
    @SuppressWarnings("unused")
    private void addUser() {
        Faker faker = new Faker();

        for (int i = 0; i < 50; i++) { // Generate 50 fake users
            User user = new User();
            Random random = new Random();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setFullName(firstName + " " + lastName);
            user.setUserName((firstName + lastName + random.nextInt(1000)).toLowerCase());
            user.setEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() +
                    random.nextInt(100) + "@" + faker.internet().domainName());
            user.setPassword(faker.internet().password(4, 6, true, true));
            user.setPhoneNumber(faker.number().digits(random.nextInt(8) + 8));
            LocalDate birthDate = LocalDate.now()
                    .minusYears(18 + random.nextInt(62))
                    .minusDays(random.nextInt(365));
            user.setDateOfBirth(birthDate);
            user.setAge(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears());
            userRepository.save(user);
        }
    }

}
