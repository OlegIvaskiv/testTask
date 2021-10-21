package com.testTask.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.testTask.model.Employee;
import com.testTask.model.Role;

public class CustomEmployeeDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Employee employee;

	public CustomEmployeeDetails(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Role role = employee.getRole();

		List<SimpleGrantedAuthority> authories = new ArrayList<>();
		authories.add(new SimpleGrantedAuthority(role.getName()));
		return authories;
	}

	public boolean hasRole(String roleName) {
		return employee.hasRole(roleName);
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getName();

	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return employee.getActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
