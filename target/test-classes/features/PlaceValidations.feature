Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is doing Successfully added using AddPlacePAI
Given Add Place Payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with "Post" http request
Then the API call got success with the status Code 200
And "scope" in response body  is "APP"
And "status" in response body  is "OK"
And Verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
  |  name |  language  |   address  |
  |  usha |  Kannada   |   assas    |
# |  Sonu |  Kannada   |   assas    |


@DeletePlace
Scenario: Verify delete place API is working sucessfully

Given DeletePlace Payload
When User calls "DeletePlaceAPI" with "Post" http request
Then the API call got success with the status Code 200
And "status" in response body  is "OK"