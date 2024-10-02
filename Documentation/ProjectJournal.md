# Sprint Journal: Weekly Scrum Reporting

## Week 1

Discussion Points
1. Technology stack to be used
2. Business logic 
3. Database type to be used and design

Outcome: 
1. Use SpringBoot with Maven builder to build backend
2. Use Hibernate ORM for relational mapping
3. ReactJS for frontend
4. Postman to be used for testing the backend API endpoints till frontend is built

Blockers:
1. Maven boilerplate to be created to begin working on backend

## Week 2

Discussion Points:
1. Model and repository creation for objects
2. Research on Hibernate approaches for mapping between tables 
3. Requirements analysis for APIs to be created for database models
4. Web security module to be added for tracking login of users

Outcome: 
1. Finalised APIs for Driver and DriverCard models

Blockers:
1. Create models for Driver and DriverCard classes

## Week 3

Discussion Points:
1. Keep task sheet updated 
2. Work on creation of rest of the models/APIs
3. Research on approaches to deploy the application to AWS

Outcome: 
1. Chaitanya, Yashwant, Krutika working on creation of APIs at backend

Blockers: 
NA

## Week 4

Discussion Points:
1. Quick updates on frontend
2. Discuss requirements from frontend for the APIs
3. Admin role to be added to the backend

Outcome: 
1. Quick updates--Chaitanya, Yashwant, Krutika to continue working on APIs
3. Chaitanya to add admin model

Blockers:
1. Sathya to update how to integrate APIs at the backend with frontend

## Week 5

Discussion Points:
1. API endpoints needed for Admin and user roles
2. Primary key for driver model should be driver's email ID/driver's license
3. Reservation module to be added

Outcome: 
1. User Driver's email ID as primary key and use the same for login too 

Blockers:
1. Vehicle and Parking_Location classes mapping is broken. Needs to be fixed before reservation can be created and tested.

## Week 6

Discussion Points:
1. Should we use Amazon S3 to store images of cars?

Outcome: 
1. Chaitanya to configure Amazon s3 dependencies and SDK

Blockers:
NA

## Week 7

Discussion Points:
1. Testing of APIs
2. CLose look at requirements of the project and missing features
3. Addition JWT validation for security
4. Decide against using S3 for storing images

Outcome: 
1. Split addition of missing features implementation
2. Yashwant to add JWT validation
3. Use static images on website than from S3 bucket

Blockers:
1. Addition of JWT validation to integrate security at frontend

## Week 8

Discussion Points:
1. API endpoints in reservation module
2. How to handle review model--comments, stars, ratings, etc.

Outcome: 
1. Reviews to be added to each reservation

Blockers:
NA

## Week 9

Discussion Points:
1. Adding missing features and documentation 
2. Support needed for frontend development
3. Any backend fixes

Outcome: 
1. This is to be handled by everybody
2. Provide support to frontend development with API backend structure, json etc.

Blockers:
1. Frontend awaiting API gateway endpoint as starting point into the application

## Week 10

Discussion Points:
1. Issues faced at frontend
2. Backend bug fixes
3. AWS Deployment done 

Outcome: 
1. Continuous integration as per input from frontend
2. AWS API gateway endpoint integrated into frontend

Blockers:
NA
