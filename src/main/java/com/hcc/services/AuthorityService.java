package com.hcc.services;

import com.hcc.enums.AuthorityEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityService {


    public List<String> getAuthorityNames() {
        return Arrays.stream(AuthorityEnum.values())
                .map(AuthorityEnum::name)
                .collect(Collectors.toList());

    }
}
