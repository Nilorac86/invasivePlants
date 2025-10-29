package com.carolin.invasiveplants.SecurityConfig;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

/**
 * Utility class for creating and deleting secure HttpOnly cookies.
 *
 * Usage examples:
 * - CookieUtil.createJwtCookie(token)
 * - CookieUtil.deleteJwtCookie()
 *
 * * <p>All endpoints that require authentication must include the following parameter:
 *  *  @CookieValue(name = "jwt", required = false) String token
 *  * </p>
 *  *   This annotation extracts the JWT value automatically from the HttpOnly cookie
 *  *   that was set during login. The token is then validated in the service or filter layer.
 */
@Component
public class CookieUtil {

    // Cookie name (same key used throughout the app)
    private static final String COOKIE_NAME = "jwt";

    // Lifetime in seconds (1 hour)
    private static final long COOKIE_MAX_AGE = 3600;

    /**
     * Creates a secure HttpOnly cookie containing a JWT.
     * @param token the JWT token to store
     * @return ResponseCookie instance ready to be added to the response header
     */
    //Create HttpOnly cookie for JWT (inaccessible from JavaScript, prevents XSS attacks)
    public ResponseCookie createJwtCookie(String token) {
        return ResponseCookie.from(COOKIE_NAME, token)
                .httpOnly(true)             // JavaScript cannot read cookie - protection against xss
                .secure(false)              // true = HTTPS required for production, false = HTTP allowed (ok for local testing)
                .path("/")                  // the cookie applies to the entire domain
                .maxAge(COOKIE_MAX_AGE)     // Cookie lifetime (seconds)
                .sameSite("None")           // allows cross-site request (necessary if frontend on other port)
                .build();
    }

    /**
     * Creates a cookie that effectively deletes the JWT cookie from the client.
     *  Deletes the JWT cookie by setting Max-Age = 0.
     * @return ResponseCookie that overwrites and expires immediately
     */
    // Remove cookie by setting maxAge to 0 and set empty value
    public ResponseCookie deleteJwtCookie() {
        return ResponseCookie.from(COOKIE_NAME, "")
                .httpOnly(true)  // JavaScript cannot read cookie - protection against xss
                .secure(false) // true = HTTPS required, false = HTTP allowed (ok for local testing)
                .path("/") // the cookie applies to the entire domain
                .maxAge(0) // deletes cookie immedietly (make it expire)
                .sameSite("None") // allows cross-site request (necessary if frontend on other port)
                .build();
    }
}