@DemoBlazeFeature
Feature: Purchasing tests
  Test function of adding item to cart and placing order

  @DemoBlazeTest		##This Test is chosen to check that the intended user experience works as intended
  Scenario: Purchase Item
  	Given On demoblaze homepage
    When I add item to cart
    And Place order by adding necessary credentials
    Then Order will be processed successfully

	@DemoBlazeTest		##This test is for checking missing input, as orders should not be placed with any credenitals
	Scenario: Place Order With Empty Credentials
		Given On demoblaze homepage
    When I add item to cart
    And Place order without credentials
    Then Order will not be placed

  @DemoBlazeTest		##This is another critical test pertain to purchasing which does not work as intended, more information will be in the Bug Report
  Scenario: Place Order With Empy Cart
  	Given On demoblaze homepage
  	And Place order by adding necessary credentials
  	Then Order will not be placed