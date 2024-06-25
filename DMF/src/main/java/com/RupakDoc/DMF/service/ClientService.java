package com.RupakDoc.DMF.service;

import com.RupakDoc.DMF.config.DateConfig;
import com.RupakDoc.DMF.dto.SignUpRequestDto;
import com.RupakDoc.DMF.model.UserEntity;
import com.RupakDoc.DMF.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private UserRepository userRepository;

    public Optional<String> login(long mobileNo) {
        Optional<UserEntity> optionalClient = userRepository.findByMobileNo(mobileNo);
        return optionalClient.isPresent() ? Optional.of(generateOtp()) : Optional.empty();
    }

    private String generateOtp() {
        // Generate a 4-digit OTP
        return String.format("%04d", (int) (Math.random() * 10000));
    }

    public Optional<UserEntity> signup(String clientId, SignUpRequestDto signUpRequestDto) {
        if (!DateConfig.isValidDate(signUpRequestDto.getDob())) {
            throw new IllegalArgumentException("Invalid date format. Please use dd-MM-yyyy");
        }

        return userRepository.findByClientId(clientId)
                .map(client -> {
                    client.setMobileNo(signUpRequestDto.getMobileNo());
                    client.setEmailId(signUpRequestDto.getEmailId());
                    client.setName(signUpRequestDto.getName());
                    client.setGender(signUpRequestDto.getGender());
                    client.setDob(signUpRequestDto.getDob());
                    client.setAddress(signUpRequestDto.getAddress());
                    deleteClientsWithMobileNoZero();
                    return userRepository.save(client);
                });
    }
    public void deleteClientsWithMobileNoZero() {
        userRepository.deleteByMobileNo(0);
    }
}
