package com.kiblerdude.achievements.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;

/**
 * Immutable domain object representing an Achievement awarded to a user.
 *
 * @author kiblerj
 *
 */
public final class UserAchievement {
    // Identifier for the user that earned the achievement
    private final String userId;
    // Identifier for the achievement that was awarded
    private final String achievement;

    @JsonCreator
    public UserAchievement(
            @JsonProperty("user_id") String userId, 
            @JsonProperty("achievement") String achievement) {
        this.userId = userId;
        this.achievement = achievement;
    }

    public String getUserId() {
        return userId;
    }

    public String getAchievement() {
        return achievement;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserAchievement:[");
        builder.append(Joiner.on(",").join(
                Joiner.on("=").join("userId", userId),
                Joiner.on("=").join("achievement", achievement)));
        builder.append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (userId == null || achievement == null)
            return false;
        if (o instanceof UserAchievement) {
            UserAchievement that = (UserAchievement) o;
            // achievement are equal if it is the same achievement name for the
            // same user
            return userId.equals(that.userId) && achievement.equals(that.achievement);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 7 + (userId != null ? userId.hashCode() : 0) + (achievement != null ? achievement.hashCode() : 0);
    }
}
