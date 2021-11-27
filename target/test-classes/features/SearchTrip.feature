Feature: Redirection to flight selection page
  
  Background:
    * Basic trip on env: sit

  Scenario: Redirection from Search to Flight selection page
    Given I have found an international trip
    When I search that trip on Find flights page
    Then I verify I am redirected to flight selection page
