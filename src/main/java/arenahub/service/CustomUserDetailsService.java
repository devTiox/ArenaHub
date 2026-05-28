package arenahub.service;

import arenahub.model.Account;
import arenahub.model.CustomUserDetails;
import arenahub.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Account not found"));

        return new CustomUserDetails(account);
    }

    public CustomUserDetails loadUserByAccountId(Long accountId) throws UsernameNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new UsernameNotFoundException("Account not found"));

        return new CustomUserDetails(account);
    }
}
