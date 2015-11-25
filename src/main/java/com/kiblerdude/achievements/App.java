package com.kiblerdude.achievements;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Lists;
import com.kiblerdude.achievements.domain.Activity;
import com.kiblerdude.achievements.domain.UserAchievement;
import com.kiblerdude.achievements.service.AchievementService;
import com.kiblerdude.achievements.service.SerializationService;
import com.kiblerdude.achievements.types.ChristmasAchievement;

/**
 * Achievements
 */
public class App 
{
    public static void main( String[] args )
    {
    	List<Achievement> achievements = Lists.newArrayList();
    	achievements.add(new ChristmasAchievement());
    	
        SerializationService serializationSvc = new SerializationService();
        AchievementService achievementsSvc = new AchievementService(achievements);
        
        List<String> dataFileNames = Lists.newArrayList("data1.txt", "data2.txt", "data3.txt", "data4.txt");
        
        // load the activity from each data file
        List<Activity> activities = dataFileNames.stream().map(fileName -> {
        	String json = "";
			try {
				json = IOUtils.toString(Files.newBufferedReader(Paths.get("src/main/resources/" + fileName)));
			} catch (Exception e) {
				e.printStackTrace();
			}
        	return serializationSvc.deserialize(json, Activity.class);
        }).filter(a -> a.isPresent()).map(a -> a.get()).collect(Collectors.toList());
        
        activities.stream().forEach(activity -> {
        	List<UserAchievement> earned = achievementsSvc.processActivity(activity);
        });
        
    }
}
