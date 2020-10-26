# new feature
# Tags: optional
@srikanya4
Feature: Job Search

  Scenario Outline: Check the advanced search happy Path
    Given JobSeeker is on landing page and open advance search
    When  He search for a job with following details "<JobTitle>", "<Location>", "<Distance>", "<SalaryMin>","<SalaryMax>","<Salarytype>","<Jobtype>"
    And   Search for the jobs
    Then  He should receive the matching jobs

    Examples:
      | JobTitle         | Location | Distance       | SalaryMin | SalaryMax | Salarytype | Jobtype |
      | Automation Tester | London   | up to 10 miles | 20000     | 50000     | Per annum   | Any   |