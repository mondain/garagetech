package com.lewdlistings.web;

import com.lewdlistings.validation.constraints.FieldMatch;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirm", message = "error.password.mismatch")
})
public class JoinForm implements Serializable {

    private static final long serialVersionUID = -6794078003590868446L;

    @NotNull(message="error.username.empty")
    @Size(min=3, max=25, message="error.username.too.short")
    @Pattern(regexp="^[a-zA-Z0-9]+$", message="error.username.illegal.chars")
    private String username;

    @NotNull(message="error.password.empty")
    @Size(min=6, max=25, message="error.password.too.short")
    private String password;

    @NotNull(message="error.confirm.empty")
    private String confirm;

    @NotNull(message="error.email.empty")
    @Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}", message="error.username.illegal.chars")
    private String email;

    @AssertTrue(message="error.agree.to.terms")
    private boolean agreeToTerms;

    public JoinForm() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAgreeToTerms() {
        return agreeToTerms;
    }

    public void setAgreeToTerms(boolean agreeToTerms) {
        this.agreeToTerms = agreeToTerms;
    }
}