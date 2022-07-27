@DGFlow11


Feature: Validate the NTM message sent after NOTOC finalization

Scenario: Validate the NTM message sent after NOTOC finalization
    Given Login to Application and switch role for REG_DG_016_021_022
    When DG Booking in CAP018 screen for REG_DG_016_021_022
    And Do DG capture in OPR026 screen for REG_DG_016_021_022
    And Do Capture DG details in OPR350 screen for REG_DG_016_021_022
    And Do DG Goods Acceptance in OPR335 screen for REG_DG_016_021_022
    And Check for XSDG message trigger in MSG005 for REG_DG_016_021_022
    And do export manifest in OPR344 fro REG_DG_016_021_022
    And Generate NOTOC and do finalization for REG_DG_016_021_022
    And Check for NTM message trigger for REG_DG_016_021_022
    And Reopen Notoc for REG_DG_016_021_022
    Then Recheck for NTM message trigger for REG_DG_016_021_022