package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.ExceptionHandler.GlobalExceptionHandler;
import com.carolin.invasiveplants.RequestDTO.LoginRequestDTO;
import com.carolin.invasiveplants.Service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//NOTE THIS IS MADE 100% WITH AI I HAVE NO CLUE 100% PASS ON TEST ATLEAST

@WebMvcTest(controllers = AuthController.class)
@Import(GlobalExceptionHandler.class) // include your exception handler
@AutoConfigureMockMvc(addFilters = false) // disables Spring Security for testing
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    void testLoginWithEmptyEmail_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"\",\"password\":\"password123\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad request"))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details[0]").value("email: Email is required"));
    }

    @Test
    void testLoginWithEmptyPassword_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@example.com\",\"password\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad request"))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details[0]").value("password: Password is required"));
    }

    @Test
    void testLoginWithBothFieldsEmpty_ShouldReturnMultipleErrors() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"\",\"password\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad request"))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details.length()").value(2))
                .andExpect(jsonPath("$.details").value(org.hamcrest.Matchers.containsInAnyOrder(
                        "email: Email is required",
                        "password: Password is required"
                )));
    }

    @Test
    void testLoginWithValidCredentials_ShouldReturnTokens() throws Exception {
        // Mock service to return fake tokens (no JWT needed)
        Map<String, String> mockTokens = Map.of(
                "accessToken", "fake-access-token",
                "refreshToken", "fake-refresh-token"
        );

        when(authService.login(anyString(), anyString())).thenReturn(mockTokens);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("fake-access-token"))
                .andExpect(jsonPath("$.refreshToken").value("fake-refresh-token"));
    }
    @Test
    void testLoginEndpointExists() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@example.com\",\"password\":\"pass\"}"))
                .andExpect(status().isOk());
    }
}
