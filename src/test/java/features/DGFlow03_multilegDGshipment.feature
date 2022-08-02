@DGFlow03
Feature: MultiLeg DG Shipment

  Scenario: MultiLeg DG Shipment
    Given Login to Application and switch role for validation of DGReg_1_4
    When perform multileg booking for a DG shipment
    Then acceptance works fine for multileg DG shipment