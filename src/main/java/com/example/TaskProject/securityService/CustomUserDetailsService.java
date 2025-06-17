package com.example.TaskProject.securityService;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.TaskProject.Exception.UserNotFound;
import com.example.TaskProject.repo.UserRepository;
import com.example.TaskProject.entity.User;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	private final UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user =    userRepo.findByUserEmail(userEmail).orElseThrow(()-> new UserNotFound(String.format("user belongs to %s", userEmail))   );
		Set <String> roles = new HashSet<>();
		 roles.add("ROLE_ADMIN");
		 return  new org.springframework.security.core.userdetails.User(user.getUserEmail(),user.getUserPassword(),userAuthority(roles));
	}
	private Collection<? extends GrantedAuthority> userAuthority (Set<String> roles){
		return roles.stream()
		 .map(SimpleGrantedAuthority::new)
		 .collect(Collectors.toList());
	}
}
