package diop.licien.cvbackend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static diop.licien.cvbackend.enums.Privilege.*;

@RequiredArgsConstructor
@Getter
public enum Role {
    ADMIN(Set.of(READ_PRIVILEGE, WRITE_PRIVILEGE, DELETE_PRIVILEGE, UPDATE_PRIVILEGE)),
    USER(Set.of(READ_PRIVILEGE));

    private final Set<Privilege> privileges;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPrivileges()
                .stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_ " + this.name()));
        return authorities;
    }
}
