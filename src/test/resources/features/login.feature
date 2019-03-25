 Feature: Login
 
 As a user, when  I go to the login page,
 I should be able to login
 
Background:         #here we can put the common background steps for each scenario

Given the user is on the login page
 
@login 
Scenario: login as teacher
  #Given the user is on the login page
  When the user logs in as a teacher
  Then the user should be logged in
  #And he is now doing whatever he wants
  #But no one is stopping him

@login
Scenario: Login as a team lead
 #Given the user is on the login page
  When the user logs in as a team lead
  Then the team lead should be logged in
  
@login2
 Scenario: login as anyone
  #Given the user is on the login page
  When the user logs using "kliversageu@cbslocal.com" and "kerrieliversage"
  Then the user should be logged in 
 
@login3
 Scenario: login as another user
  #Given the user is on the login page
  When the user logs using "rbarstowk@cyberchimps.com" and "reneebarstow"
  Then the user should be logged in 
  And there should be 7 rooms
  
  
  #When I search for "flights"
  #Then I should see 2 results 



 
  
  