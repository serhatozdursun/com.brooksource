Feature: Study Case

  Scenario: Search QA Job
    Given Open "Firefox" and get "https://www.brooksource.com/"
    And Hover mouse over FIND WORK
    And Select SEEK_A_POSITION from FIND WORK
    And Input "tester" in to the job search field
    And Click on the magnifying glass to execute the search
    And Store 1. job title to compare
    When Click on the 1. available position
    Then Assert that the stored job title and the displayed job title are the same

