package com.carolin.invasiveplants.JwtAuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {
    private static final Logger logger = LoggerFactory.getLogger(TokenBlacklistService.class);

    // In-memory storage for blacklisted tokens
    // Key: token, Value: expiration timestamp (epoch milliseconds)
    private final Map<String, Long> blacklist = new ConcurrentHashMap<>();

    //Add token to blacklist
    public void addBlacklist(String token, Long expirationTime) {
        blacklist.put(token, expirationTime);
        logger.info("Token blacklisted. total blacklisted tokens: {}", blacklist.size());
        cleanupExpiredTokens();
    }

    //Check if token is blacklisted
    public boolean isBlacklisted(String token) {
        Long expirationTime = blacklist.get(token);

        if (expirationTime == null) {
            return false;
        }

        // Auto-cleanup: remove expired tokens
        if (System.currentTimeMillis() > expirationTime) {
            blacklist.remove(token);
            logger.debug("Removed expired token from blacklist");
            return false;
        }
        return true;
    }

    private void cleanupExpiredTokens() {
        long currentTime = System.currentTimeMillis();
        int removedTokens = 0;

        for (Map.Entry<String, Long> entry : blacklist.entrySet()) {
            if (currentTime > entry.getValue()) {
                blacklist.remove(entry.getKey());
                removedTokens++;
            }
        }
        if (removedTokens > 0) {
            logger.info("cleaned up {} expired tookens from blacklist", removedTokens);
        }
    }

    // Get blacklist size (for debugging)
    public int getBlacklistSize() {
        cleanupExpiredTokens();
        return blacklist.size();
    }
}
