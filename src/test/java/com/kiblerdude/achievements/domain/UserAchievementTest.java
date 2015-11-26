package com.kiblerdude.achievements.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserAchievementTest {

    @Test
    public void testEquals() {
                
        UserAchievement a1 = new UserAchievement("user", "COOL");
        UserAchievement a2 = new UserAchievement("user", "COOL");
        UserAchievement a3 = new UserAchievement("user2", "COOL");
        UserAchievement a4 = new UserAchievement("user", "DIFFERENT");
        
        assertTrue(a1.equals(a1));
        assertFalse(a1.equals(null));
        assertTrue(a1.equals(a2));
        assertFalse(a1.equals(a3));
        assertFalse(a1.equals(a4));
        assertFalse(a1.equals(new Object()));
    }

}
