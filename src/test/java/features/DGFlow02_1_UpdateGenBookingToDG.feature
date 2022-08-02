@DGFlow02
Feature: Update a General shipment and a TC shipment to DG

  @DG2
  Scenario: Update a General shipment to DG
    Given Login to Application and switch role for validation of DG2
    When perform booking for General commodity for DG2
    Then verify booking can be updated to DG and acceptance works fine for DG2

