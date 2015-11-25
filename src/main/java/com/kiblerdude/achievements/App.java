package com.kiblerdude.achievements;

import static java.lang.String.format;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.google.common.collect.Lists;
import com.kiblerdude.achievements.domain.Activity;
import com.kiblerdude.achievements.domain.UserAchievement;
import com.kiblerdude.achievements.service.AchievementService;
import com.kiblerdude.achievements.service.SerializationService;
import com.kiblerdude.achievements.types.ChristmasAchievement;

/**
 * Achievements App
 * 
 * Loads user activity from data files and outputs any achievements earned.
 */
public class App 
{
	private static final Logger LOG = Logger.getLogger(App.class);
	
	private final List<String> files;
	private final SerializationService serializationSvc;
    private final AchievementService achievementsSvc; 	
	
	public App(List<String> files, List<Achievement> achievements) {
		this.files = Collections.unmodifiableList(files);
		this.serializationSvc = new SerializationService();
		this.achievementsSvc = new AchievementService(achievements); 
	}
	
	/**
	 * Processes the achievements earned from any user activities.
	 * 
	 * @throws IOException
	 */
	public void processAchievements() throws IOException {
    	LOG.info("Starting Activity Achievement processor.");
        
    	List<Activity> activities = loadActivities();
    	
    	LOG.debug(format("%s activities loaded", activities.size()));
    	
    	List<UserAchievement> earnedAchievements = processActivities(activities);
    	
    	LOG.debug(format("%s total achievements earned", earnedAchievements.size()));
    	
    	saveAchievements(earnedAchievements);
    	
    	LOG.debug("Achievements saved");
		
    	LOG.info("Finished processing Activity Achievements.");
	}
	
	/**
	 * Helper method to load the data files and parse the json into the
	 * {@link Activity} they represent.
	 * 
	 * @return A list of {@link Activity} objects that were loaded from the file.
	 * @throws IOException if there was a problem reading any of the data files.
	 */
	private List<Activity> loadActivities() throws IOException {
              
        List<Activity> activities = Lists.newArrayList();
        
        for (String fileName : files) {
        	// load the activity from each data file  
        	String json = IOUtils.toString(Files.newBufferedReader(Paths.get(fileName)));
        	Optional<Activity> activity = serializationSvc.deserialize(json, Activity.class);
        	
        	if (activity.isPresent()) {
        		activities.add(activity.get());
        	}        	
        }
        
        return Collections.unmodifiableList(activities);
	}
	
	/**
	 * Helper method to process the provided list of <code>activities</code> and return
	 * a list of {@link UserAchievement} objects earned from any of the activities.
	 * 
	 * @param activities list of {@link Activity} to check
	 * @return list of {link UserAchievement}
	 */
    private List<UserAchievement> processActivities(List<Activity> activities) {
        
        List<UserAchievement> earnedAchievements = Lists.newArrayList();
        
        for (Activity activity : activities) {
        	// process each activity and collect any achievements earned by user.
        	earnedAchievements.addAll(achievementsSvc.processActivity(activity));        
        }

        return earnedAchievements;
    }
    
    /**
     * Helper method to serialize and save the provided <code>earnedAchievements</code> objects to files.
     * 
     * @param earnedAchievements The {@link UserAchievement} objects to save.
     * @throws IOException if the objects failed to serialize or save to a file.
     */
    private void saveAchievements(List<UserAchievement> earnedAchievements) throws IOException {
    	int i = 1;
    	for (UserAchievement achievement : earnedAchievements) {
    		String saveFileName = format("earned_achievement_%d.txt", i);
    		LOG.debug(format("Saving %s to file %s", achievement, saveFileName));
    		if (!serializationSvc.serialize(achievement, Paths.get(saveFileName))) {
    			LOG.error("Failed to save achievement!");
    			throw new IOException("Failed to save achievement");
    		}
    		i++;
    	} 
    }
	
    /**
     * Main program
     * @param args not used
     */
    public static void main( String[] args )
    {
    	// List of possible achievements (currently only the christmas achievement, but could add others)
    	// Interesting idea would be to use annotations and build the list with google reflections library to avoid 
    	// adding new achievements to this list.
    	//
    	List<Achievement> achievements = Lists.newArrayList();
    	achievements.add(new ChristmasAchievement()); 	
        
        // Load the files with the data.
        List<String> dataFileNames = Lists.newArrayList("data1.txt", "data2.txt", "data3.txt", "data4.txt").stream().map(fileName -> "src/main/resources/" + fileName).collect(Collectors.toList());
        
        try {
			new App(dataFileNames, achievements).processAchievements();
		} catch (IOException e) {
			LOG.error(e);
		}
    }
}
