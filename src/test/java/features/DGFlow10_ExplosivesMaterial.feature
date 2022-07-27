@DGFlow10
Feature: Validate DG Shipment Creation and Acceptance involving Explosives material

  Scenario: Validate DG Shipment Creation and Acceptance involving Explosives material
    Given Login to Application and switch role for REG_DG_009
    And DG Booking in CAP018 screen for REG_DG_009
    And Do DG capture in OPR026 screen for REG_DG_009
    And Do Capture DG details in OPR350 screen for REG_DG_009
    And Do DG Goods Acceptance in OPR335 screen for REG_DG_009
    And Check for XSDG message trigger in MSG005 for REG_DG_009