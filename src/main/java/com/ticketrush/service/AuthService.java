package com.ticketrush.service;

import com.ticketrush.entity.Role;
import com.ticketrush.entity.User;
import com.ticketrush.model.respone.AuthRespone;
import com.ticketrush.model.resquest.AuthenticationRequest;
import com.ticketrush.model.resquest.RegisterRequest;
import com.ticketrush.repository.IUserRepository;
import com.ticketrush.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtil;

    /**
     * Đăng ký user mới với role mặc định là ROLE_USER
     * Password được mã hóa bằng BCrypt trước khi lưu vào DB
     */
    @Override
    public void register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username đã tồn tại: " + request.getUsername());
        }

        User user = new User();
        user.setUsername(request.getUsername());
        // Luôn mã hóa password trước khi lưu - KHÔNG BAO GIỜ lưu plain text
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER); // Mặc định người dùng mới là ROLE_USER
        userRepository.save(user);
    }

    @Override
    public AuthRespone authenticate(AuthenticationRequest authRequest) {
        // Bước 1: Xác thực username/password qua AuthenticationManager
        // Nếu sai thì Spring Security tự ném BadCredentialsException
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        // Bước 2: Load UserDetails để lấy thông tin user (role, username)
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        // Bước 3: Tạo JWT token từ userDetails
        String token = jwtUtil.generateToken(userDetails);

        // Bước 4: Build response trả về client
        AuthRespone response = new AuthRespone();
        response.setAccess_token(token);
        response.setRoles(userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .toList());
        return response;
    }
}
