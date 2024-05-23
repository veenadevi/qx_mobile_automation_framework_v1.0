Feature:  contact app
  Description: contact app where we have to fill the details

  @smoke
  Scenario Outline:Save the contact
    Given I have installed the application
    When I have to do the operation"<name>","<email>","<number>"
    Then I have to see the result
    Examples:
      | name    | email             | number      |
#      | krushna | krushna@gmail.com | 97872662626 |
      | hk      | hk@gmail.com      | 1234567890  |


