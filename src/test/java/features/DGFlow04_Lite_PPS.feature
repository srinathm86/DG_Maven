@DGFlow04
Feature: Validate DG shipment for lite screen and with PPS product

  Scenario: Validate DG shipment for lite screen with PPS
    Given Login to Application and switch role for validation of DG7_17
    When perform boking for DG in lite screen for DG7_17
    Then acceptance should work fine DG7_17
