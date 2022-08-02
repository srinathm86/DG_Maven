@DGFlow01
Feature: DG Notoc Over pack and All pack test

  Scenario:  DG Notoc Over pack and All pack test
    Given Login to Application and switch role for validation of DG Over and All Pack
    When booking is performed for multiple DG goods
    Then verify OverPack and AllPack is successfully done
