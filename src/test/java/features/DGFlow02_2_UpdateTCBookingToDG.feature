#@DGFlow02
Feature: Update a General shipment and a TC shipment to DG

  Scenario: Update a TC shipment to DG
    Given Login to Application and switch role for validation of DG3
    When perform booking for TC commodity for DG3
    Then verify booking can be updated to DG and acceptance works fine for DG3
