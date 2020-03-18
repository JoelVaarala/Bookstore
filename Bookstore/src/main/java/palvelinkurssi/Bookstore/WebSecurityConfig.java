package palvelinkurssi.Bookstore;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import palvelinkurssi.Bookstore.web.UserDetailsServiceBS;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserDetailsServiceBS userDetailsService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out ++ haettavat tyylit yms kaikille avoinna
        .and()
        .authorizeRequests()
         /*
        .antMatchers("/", "/booklist").permitAll() // sallittuja osoit. kaikille ("permitAll()")
        .antMatchers("/save", "/save/**", "/edit/**", "/newbook").hasAnyRole("USER","ADMIN") // user ja admin sallitut url:t
        .antMatchers("/delete/{id}").hasRole("ADMIN")	// vain admin voi tehdä poiston
        .anyRequest().authenticated() // anyReq --> "else" jos ei sovi niin kaikki muut endpointit autentikaation alaisesti
          */
          .and()
      .formLogin()
          .loginPage("/login") // (ei tarvita login endpointtia) -- kirjasto tarjoaa valmiin login ratkaisun!
          .defaultSuccessUrl("/booklist") // kun sisäänkirjautuminen onnistuu --> ohjaa tähän endpointtiin
          .permitAll()
          .and()
      .logout()
          .permitAll()
        	.and();
    }
    
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
