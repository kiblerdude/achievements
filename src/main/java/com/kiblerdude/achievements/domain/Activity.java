package com.kiblerdude.achievements.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;

/**
 * Immutable domain object representing an Activity performed by an user, such
 * as running, swimming, or playing a sport.
 *
 * @author kiblerj
 *
 */
public final class Activity {
    // Identifier for the user.
    private final String userId;
    // Identifier for the activity the user performed
    private final String activityType;
    // The date the user performed the activity in the form
    // "2015-08-24T20:00:08"
    private final String dateTime;
    // Data associated with the activity
    private final ActivityMetric metric;

    @JsonCreator
    public Activity(@JsonProperty("user_id") String userId, @JsonProperty("activity_type") String activityType,
            @JsonProperty("date_time") String dateTime, @JsonProperty("metric") ActivityMetric metric) {
        this.userId = userId;
        this.activityType = activityType;
        this.dateTime = dateTime;
        this.metric = metric;
    }

    public String getUserId() {
        return userId;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public ActivityMetric getMetric() {
        return metric;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Activity:[");
        builder.append(Joiner.on(",").join(
                Joiner.on("=").join("userId", userId),
                Joiner.on("=").join("activity", activityType),
                Joiner.on("=").join("date", dateTime)));
        builder.append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (userId == null || activityType == null || dateTime == null)
            return false;
        if (o instanceof Activity) {
            Activity that = (Activity) o;
            // Activities are equal if it is the same user, type, and date
            return userId.equals(that.userId) && activityType.equals(that.activityType)
                    && dateTime.equals(that.dateTime);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 7 + (userId != null ? userId.hashCode() : 0) + (activityType != null ? activityType.hashCode() : 0)
                + (dateTime != null ? dateTime.hashCode() : 0);
    }
}
