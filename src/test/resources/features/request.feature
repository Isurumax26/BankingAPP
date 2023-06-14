Feature: Get Balance

    Scenario: Customer Request Cumulative Balance
        Given All the transaction
        When Customer "2" request cumulative balance
        Then Application should return 2700.00

    Scenario: Customer Request Monthly Balance
        Given All the transaction
        When Customer "1" request monthly balance on "2020-03"
        Then Application should return -1700.00

    Scenario: Customer Request Monthly Balance
        Given All the transaction
        When Customer "1" request monthly balance on "2019-03"
        Then Application should return 0.0