package com.one_time_token;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OttSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

    /**
     * <h1>One-Time Token (OTT) Authentication</h1>
     * <p>
     *     Spring Security provides support for One-Time Token (OTT) authentication through the
     *     {@code oneTimeTokenLogin()} DSL. This feature allows users to authenticate using a
     *     one-time token, which is typically delivered via email, SMS, or other communication
     *     channels. Unlike One-Time Passwords (OTP), OTT does not require any initial setup
     *     from the user, making it a convenient and secure authentication method.
     * </p>
     *
     * <h2>Key Features</h2>
     * <ul>
     *     <li><strong>No Initial Setup:</strong> Users do not need to configure anything in advance.</li>
     *     <li><strong>Custom Token Delivery:</strong> Tokens are delivered to users via a custom
     *         {@link OneTimeTokenGenerationSuccessHandler}, allowing flexibility in how tokens
     *         are sent (e.g., email, SMS, etc.).</li>
     *     <li><strong>Server-Side Token Generation:</strong> Tokens are generated on the server
     *         using the {@link OneTimeTokenService}.</li>
     *     <li><strong>Magic Link Support:</strong> Tokens can be embedded in a "magic link"
     *         sent to the user, simplifying the login process.</li>
     * </ul>
     *
     * <h2>How It Works</h2>
     * <ol>
     *     <li><strong>Token Request:</strong> The user requests a token by submitting their
     *         username or other identifier.</li>
     *     <li><strong>Token Delivery:</strong> The token is generated and delivered to the user
     *         via a custom handler (e.g., email with a magic link).</li>
     *     <li><strong>Token Submission:</strong> The user submits the token to the one-time
     *         token login endpoint.</li>
     *     <li><strong>Authentication:</strong> If the token is valid, the user is logged in.</li>
     * </ol>
     *
     * <h2>Configuration</h2>
     * <p>
     *     The {@code oneTimeTokenLogin()} DSL can be used alongside {@code formLogin()} to
     *     enable OTT authentication. Customization options include changing the token
     *     generation URL, disabling the default submit page, and providing a custom
     *     {@link OneTimeTokenService} for token management.
     * </p>
     */


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .fragment(null)
                .path("/login/ott")
                .queryParam("token", oneTimeToken.getTokenValue());

        var magicLink = builder.toUriString();

        // send email (JavaMail, SendGrid) or SMS
        System.out.println("Magic Link: " + magicLink);
    }
}