package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.jcaa.usersmanagement.domain.exception.InvalidCredentialsException;
import com.jcaa.usersmanagement.domain.exception.UserAlreadyExistsException;
import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.LoginRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import jakarta.validation.ConstraintViolationException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserManagementCliTest {

  private static final UserResponse USER =
      new UserResponse("u-1", "Ada Lovelace", "ada@example.com", "ADMIN", "ACTIVE");

  @Mock private UserController userController;

  @Test
  void startRoutesMenuOperationsAndDisplaysSuccessAndErrorMessages() {
    when(userController.listAllUsers())
        .thenReturn(List.of(USER))
        .thenReturn(List.of())
        .thenThrow(new IllegalStateException("list failed"))
        .thenThrow(new ConstraintViolationException("invalid request", Set.of()));
    when(userController.findUserById("u-1")).thenReturn(USER);
    when(userController.findUserById("missing"))
        .thenThrow(UserNotFoundException.becauseIdWasNotFound("missing"));
    when(userController.createUser(any(CreateUserRequest.class)))
        .thenReturn(USER)
        .thenThrow(UserAlreadyExistsException.becauseEmailAlreadyExists("ada@example.com"));
    when(userController.updateUser(any(UpdateUserRequest.class))).thenReturn(USER);
    when(userController.updateUser(org.mockito.ArgumentMatchers.argThat(
            request -> request.id().equals("missing"))))
        .thenThrow(UserNotFoundException.becauseIdWasNotFound("missing"));
    doNothing().when(userController).deleteUser("u-1");
    doThrow(UserNotFoundException.becauseIdWasNotFound("missing"))
        .when(userController)
        .deleteUser("missing");
    when(userController.login(any(LoginRequest.class)))
        .thenReturn(USER)
        .thenThrow(InvalidCredentialsException.becauseCredentialsAreInvalid());

    final String output =
        run(
            String.join(
                "\n",
                "not a number",
                "99",
                "1",
                "1",
                "1",
                "1",
                "2",
                "",
                "u-1",
                "2",
                "missing",
                "3",
                "u-1",
                "Ada Lovelace",
                "ada@example.com",
                "Password123",
                "ADMIN",
                "3",
                "u-2",
                "Ada Lovelace",
                "ada@example.com",
                "Password123",
                "ADMIN",
                "4",
                "u-1",
                "Ada Lovelace",
                "ada@example.com",
                "",
                "ADMIN",
                "ACTIVE",
                "4",
                "missing",
                "Ada Lovelace",
                "ada@example.com",
                "new password",
                "ADMIN",
                "ACTIVE",
                "5",
                "u-1",
                "5",
                "missing",
                "6",
                "ada@example.com",
                "Password123",
                "6",
                "ada@example.com",
                "wrong",
                "0",
                ""));

    assertTrue(output.contains("Invalid input. Please enter a number."));
    assertTrue(output.contains("Invalid option. Please try again."));
    assertTrue(output.contains("No users found."));
    assertTrue(output.contains("Unexpected error: list failed"));
    assertTrue(output.contains("Validation errors:"));
    assertTrue(output.contains("Value cannot be blank. Please try again."));
    assertTrue(output.contains("User created successfully."));
    assertTrue(output.contains("A user with email 'ada@example.com' already exists."));
    assertTrue(output.contains("User updated successfully."));
    assertTrue(output.contains("User deleted successfully."));
    assertTrue(output.contains("Login successful. Welcome!"));
    assertTrue(output.contains("Correo o contraseña incorrectos."));
    assertTrue(output.contains("Goodbye!"));
  }

  private String run(final String input) {
    final ByteArrayOutputStream output = new ByteArrayOutputStream();
    final ConsoleIO console =
        new ConsoleIO(new Scanner(input), new PrintStream(output, true, StandardCharsets.UTF_8));
    new UserManagementCli(userController, console).start();
    return output.toString(StandardCharsets.UTF_8);
  }
}
