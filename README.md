# Getting Started

### Reference Documentation
Use Java 17 for running the app.
Once the app is up and running, try to access the in memory h2 DB at the url localhost:8080/h2-console.
Username is "sa" and password is "password"
DB name is jdbc:h2:mem:mydb

PostMan Sample Requests:
1) For creating the resources:
curl --location 'http://localhost:8080/evaluation/sheets' \
--header 'Content-Type: application/json' \
--data '[
    {
        "testeeId": "341",
        "subjects": [
            {
                "subject": "maths",
                "totalQuestions": 100,
                "correct": 60,
                "incorrect": 40
            },
            {
                "subject": "science",
                "totalQuestions": 100,
                "correct": 72,
                "incorrect": 28
            },
            {
                "subject": "general",
                "totalQuestions": 100,
                "correct": 75,
                "incorrect": 25
            }
        ]
    },
    {
        "testeeId": "342",
        "subjects": [
            {
                "subject": "maths",
                "totalQuestions": 100,
                "correct": 50,
                "incorrect": 50
            },
            {
                "subject": "science",
                "totalQuestions": 100,
                "correct": 78,
                "incorrect": 22
            },
            {
                "subject": "general",
                "totalQuestions": 100,
                "correct": 85,
                "incorrect": 15
            }
        ]
    }
]'


2) For displaying the list sorted by totalScore in descending and testeeId in ascending:
curl --location --request GET 'http://localhost:8080/evaluation/scores' \
--header 'Content-Type: application/json' \
--data '{
    "scoreRange": "79-90"
}'


Build the maven package:
--> mvn clean install

Run Spring Boot app

----TEST CASES ARE NOT WRITTEN YET DUE TO SOME ISSUES THAT I ENCOUNTERED----
----Please give me some more time to wrap this up Since I am Working parallel on my work and the assessment----





