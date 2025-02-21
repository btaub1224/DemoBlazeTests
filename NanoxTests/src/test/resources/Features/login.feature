@DemoBlazeFeature
Feature: Login Tests
  Test function of sign up and login

  @DemoBlazeTest		##This Test is chosen to ensure logging in works as that is a vital feature for any storefront
  Scenario: Create User
  	Given On demoblaze webpage
    And Username and password that have not been used before
    When I sign up using those credentials
    And Login to verify user creation
    Then Login will be successful
