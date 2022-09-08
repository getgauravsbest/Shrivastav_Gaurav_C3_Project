import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class RestaurantTest {
    Restaurant restaurant;
    Restaurant restaurant_new;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        // Start time is 1 hr before local & end time is 1 hr after Loc time
        LocalTime openingTime =  LocalTime.now().minusHours(1); // mock Restaurant start time to be passed as hr before local time                                                                               // an hr before
        LocalTime closingTime =  LocalTime.now().plusHours(1);     //mock Restaurant end time to be passed as hr after local time
        restaurant_new =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        assertEquals(true,restaurant_new.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        // Restaurant Start & close time before local time
        LocalTime openingTime =  LocalTime.now().minusHours(3); // mock Restaurant start time to be passed as 3 hr before local time                                                                               // an hr before
        LocalTime closingTime =  LocalTime.now().minusHours(2);     //mock Restaurant end time to be passed as 2 hr before local time
        restaurant_new =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        assertEquals(false,restaurant_new.isRestaurantOpen());

    }

    @Test
    //To test method isRestaurantOpen() - positive case
    public void whenRestaurantisOpen(){

        LocalTime openingTime =  LocalTime.now().minusHours(1); // mock Restaurant start time to be passed as hr before local time                                                                               // an hr before
        LocalTime closingTime =  LocalTime.now().plusHours(1);     //mock Restaurant end time to be passed as hr after local time
        restaurant_new =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        assertEquals(true,restaurant_new.isRestaurantOpen());
    }

    @Test
    //To test method isRestaurantOpen() - negative case
    public void whenRestaurantisNotOpen(){

        // Restaurant Start & close time before local time
        LocalTime openingTime =  LocalTime.now().minusHours(3); // mock Restaurant start time to be passed as 3 hr before local time                                                                               // an hr before
        LocalTime closingTime =  LocalTime.now().minusHours(2);     //mock Restaurant end time to be passed as 2 hr before local time
        restaurant_new =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        assertEquals(false,restaurant_new.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    //to test getMenu() method - positive & Boundary  test case
    public void getmenuwhenfound() throws itemNotFoundException {
        //Add one restaurant & one menu to the restaurant
//        LocalTime openingTime = LocalTime.parse("10:30:00");
//        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

        //No item is added to menu
        //Size of menu should be 0 as no item has been added
        int initialMenuSize = restaurant.getMenu().size();
        assertEquals(0,restaurant.getMenu().size());

        //Now adding item
        restaurant.addToMenu("Sweet corn soup",119);
        //Size of menu should be 1
        initialMenuSize = restaurant.getMenu().size();
        assertEquals(1,restaurant.getMenu().size());

        //After removing the menu item, the menu sie is zero
        restaurant.removeFromMenu("Sweet corn soup");
        assertEquals(0,restaurant.getMenu().size());
    }



    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
//        LocalTime openingTime = LocalTime.parse("10:30:00");
//        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
//        LocalTime openingTime = LocalTime.parse("10:30:00");
//        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //New mothod for testing the price - part 3 of the submnission
    @Test
    //   Get the price of menu selected
    public void testcostofmenu( ){
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.ShowCostofOrder("Sweet corn soup","Vegetable lasagne");
    }

}