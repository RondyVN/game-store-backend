package com.api.gameshop.DTO;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Data
@Jacksonized
@RequiredArgsConstructor
public class UserDTO {
    String userEmail;
    String username;
    List<Map<String, Long>> games;
    Double price;
}
