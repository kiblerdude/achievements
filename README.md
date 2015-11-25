# Achievements

Achievement system coding exercise.

Loads user activity from data files, determines any achievements earned, and outputs the earned achievements to data files.

### Requirements

- Java 8
- Maven 3.x

### Instructions

Standard Maven lifecylce may be used to compile, test, and execute the program.

1. Clone the project
2. Navigate to the project directory
3. Compile the project with maven
4. Run the project with maven

For example:

	$ git clone git@github.com:kiblerdude/achievements.git
	$ cd achievements
	$ mvn clean install
	$ mvn exec:java

Executing the project will generate some logs (unless you modify the `log4j.xml`)

	2015-11-25 18:11:11,811 INFO  [App] - Starting Activity Achievement processor.
	2015-11-25 18:11:11,899 DEBUG [App] - 4 activities loaded
	...
	2015-11-25 18:11:11,946 DEBUG [App] - Achievements saved
	2015-11-25 18:11:11,946 INFO  [App] - Finished processing Activity Achievements.

The project will save each achievement earned to a file in the current working directory.  The file names are prefixed with `earned_achievement_` followed by a incrementing number.  These files contain a JSON serialized representation of the achievement earned by the user.

For example:

	{"achievement":"CHRISTMAS","userId":"joe"}