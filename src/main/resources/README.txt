
Code Exercise Intructions

Your task is to evaulate the provided dataset and award an achievement when
appropriate.  An achievement is recognition of an accomplishment, your code
may represent this any way you see fit.

Sample input:
    {
        "user_id": "joe",
        "activity_type": "fuel",
        "date_time": "2015-08-24T20:00:08",
        "metric": {
            "type": "fuel",
            "data": [ 220, 123, 88 ]
        }
    }

    user_id: Identifier for the user.

    activity_type: Identifier for the activity (e.g., Running, Swimming,
        Basketball, etc.)

    date_time: The activity date.

    metric: Data for the activity, it includes a type (fuel, distance, etc.)
        as well as numerical values.  The numerical values represent the
        metric type over time (i.e. fuel earned for each minute).

While the system may eventually support many types of achievements, your
task for this exercise is to enable it to award 1 type of achievment only. The
achievement you will be awarding will be the "CHRISTMAS" achievement.  The
requirements to earn this achievement are as follows:

    1) The activity_type and metric type must be "fuel"
    2) The activity must be performed on December 25th
    3) The total fuel for the activity must be greater than 300

Your system should return your representation of the achievement only if
those conditions are met.

Some things to consider when creating your solution:

    1) Coding Style
    2) Correctness
    3) Extensibility

There are many good solutions to this exercise, we're not looking for a
particular solution.  We are not even looking for a specific language, feel
free to use language you would like.  You may submit your solution to us
however you'd like, just let us know how to access it and execute it.  Your
code can be executed from the command line, or if you prefer something
different, no problem (just let us know how to run it).

Have fun with this and good luck!
