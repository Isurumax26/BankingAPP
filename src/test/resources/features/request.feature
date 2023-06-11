Feature: Cumulative Balance

    Scenario: Customer Request Cumulative Balance
        Given All the transaction
        When Customer "1" request cumulative balance
        Then Application should return 2700.00