Feature: Create User Functionality

  In order to use the system
  As a valid user
  I want to create a user

  # Scenario Create User Successfully
  @Create_User_Successfully
  Scenario: Create User

    Given that wants to create user
    When make a post request
    Then return status 200

  # Scenario search created user
  @Search_Search_User
  Scenario: Search User

    Given that wants to search user
    When make a get request
    Then return user data
    And return status 200

  # Scenario Login Failed
  @Scenario_Login_Failed
  Scenario: Delete user

    Given that wants to delete user
    When make a delete request
    Then return status 200
