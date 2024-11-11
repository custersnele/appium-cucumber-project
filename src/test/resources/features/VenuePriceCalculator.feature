Feature: Venue price calculator

  Background:
    Given Jim has opened the venue application

  Scenario: Jim is presented with venue options
    When Jim clicks on the venue picker
    Then the City Hall option should be present
    And the Main Building option should be present
    And the Retro Lounge option should be present

  Scenario: Jim enters number of guest that is above the limit of the venue
    When Jim enters 201 for the number of guests
    And clicks on the calculate button
    Then the validation message "Guest count exceeds the capacity of the selected venue." is displayed

  Scenario Outline: Jim calculates venue costs
    When Jim clicks on the venue picker
    And the "<venue>" option is selected
    And Jim enters <numberOfGuests> for the number of guests
    And selects "<levelOfService>" option for the Service level
    And clicks on the calculate button
    Then the result "<result>" is displayed

    Examples:
      | venue         | numberOfGuests | levelOfService | result               |
      | City Hall     | 150            | Basic          | Total cost is: 15000 |
      | Main Building | 300            | Premium        | Total cost is: 40000 |
      | Retro Lounge  | 500            | Basic          | Total cost is: 57000 |
