# electronic-business
College project

# Pusher Events and Payloads Documentation

## `sendAuctionStartedNotification`

- **Event Name:** `auctionStarted`
- **Description:** This event is triggered when an auction starts.
- **Payload:**
    - **Type:** Object
    - **Attributes:**
        - `creator` (String): The unique identifier of the creator of the auction.
        - `item` (String): The item being auctioned.

## `sendAuctionFinishedNotification`

- **Event Name:** `auctionFinished`
- **Description:** This event is triggered when an auction finishes.
- **Payload:**
    - **Type:** Object
    - **Attributes:**
        - `bidders` (List of String): The list of unique identifiers of bidders who participated in the auction.
        - `auctionCreator` (String): The unique identifier of the creator of the auction.
        - `winner` (String): The unique identifier of the winning bidder.
        - `item` (String): The item being auctioned.

## `sendBidCreatedNotification`

- **Event Name:** `bidCreated`
- **Description:** This event is triggered when a bid is created.
- **Payload:**
    - **Type:** Object
    - **Attributes:**
        - `id` (long): The unique identifier of the bid.
        - `auctionId` (long): The unique identifier of the auction the bid belongs to.
        - `bidder` (String): The unique identifier of the bidder.
        - `amount` (double): The amount of the bid.
        - `bidDifference` (double): The difference between the bid amount and the previous bid amount.

