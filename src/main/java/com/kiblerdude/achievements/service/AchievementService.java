package com.kiblerdude.achievements.service;

import static java.lang.String.format;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.collect.Lists;
import com.kiblerdude.achievements.Achievement;
import com.kiblerdude.achievements.domain.Activity;
import com.kiblerdude.achievements.domain.UserAchievement;

/**
 * Service that manages Achievements and processes user {@link Activity} to
 * generate an {@link UserAchievement} when earned.
 * 
 * @author kiblerj
 *
 */
public final class AchievementService {

    private static final Logger LOG = Logger.getLogger(AchievementService.class);

    private final List<Achievement> achievements;

    public AchievementService(List<Achievement> achievements) {
        this.achievements = Collections.unmodifiableList(achievements);
    }

    /**
     * Processes a user {@link Activity} and returns a list of
     * {@link UserAchievement} the user earned from the activity.
     * 
     * @param activity
     *            The user's {@link Activity}.
     * @return List of {@link UserAchievement} earned from the activity.
     */
    public List<UserAchievement> processActivity(Activity activity) {
        if (activity == null) {
            LOG.error("Activity is null!");
            return Lists.newArrayList();
        }

        List<UserAchievement> achievementsEarned = Lists.newArrayList();

        for (Achievement achievement : achievements) {
            LOG.debug(format("Checking if user %s earned the %s achievement",
                    activity.getUserId(), achievement.getName()));
            if (achievement.isAchieved(activity)) {
                LOG.debug(format("User %s has earned the %s achievement!",
                        activity.getUserId(), achievement.getName()));
                achievementsEarned.add(new UserAchievement(activity.getUserId(), achievement.getName()));
            }
        }

        return Collections.unmodifiableList(achievementsEarned);
    }

}
